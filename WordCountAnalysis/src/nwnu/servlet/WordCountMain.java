package nwnu.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nwnu.wordcount.Count1;


public class WordCountMain extends HttpServlet{
	String file="";
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String cmd=request.getParameter("cmd");
		switch(cmd){
		case "getfilename":
			getfilename(request,response);
			break;
		case "givenWordCount":
			givenWordCount(request,response);
			break;
		case "showHistogram":
			showHistogram(request,response);
			break;
		case "countRowNumber":
			countRowNumber(request,response);
			break;
		case "countCharacterNumber":
			countCharacterNumber(request,response);
			break;
	}
}
	
		/**
		 * 统计各类字符数
		 * @param request
		 * @param response
		 * @throws IOException 
		 * @throws ServletException 
		 */
		private void countCharacterNumber(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
			String filecontent = "";//用来存放读取到的文件内容信息
			long startTime=System.currentTimeMillis();
			long totaltime=0;
	        try {
	            FileInputStream fis = new FileInputStream(file);
	            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	            String str = "";
	            
	            try {
	                //一行一行读取
	                while ((str = br.readLine()) != null){
	                	filecontent = filecontent + str;
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }catch (FileNotFoundException e){
	            e.printStackTrace();
	        }
			
			int letter=0,blank=0,digit=0,other=0;
			
			for(int i=0;i<filecontent.length();i++)
			{
				char c=filecontent.charAt(i);
				if (c>='a'&c<='z'|c>='A'&c<='Z')
					letter++;
				else if(c==' ')
					blank++;
				else if(c>='0'&c<='9')
					digit++;
				else
					other++;
			}
			long endTime=System.currentTimeMillis();
			totaltime=endTime-startTime;
			System.out.println("耗时："+totaltime);
			
			System.out.println("统计结果如下：\n"+"英文字母个数为："+letter+"\n空格字符的个数为:"+blank
						+"\n数字字符的个数为："+digit+"\n其他字符的个数为："+other);
			
			request.setAttribute("letter", letter);
			request.setAttribute("blank", blank);
			request.setAttribute("digit", digit);
			request.setAttribute("other", other);
			request.setAttribute("file", file);
			request.setAttribute("totaltime", totaltime);
			request.getRequestDispatcher("/html/countCharacterNumber.jsp").forward(request, response);
			
		}
		/**
		 * 统计文本行数
		 * @param request
		 * @param response
		 * @throws IOException 
		 * @throws ServletException 
		 */
		private void countRowNumber(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			long startTime=System.currentTimeMillis();
			long totaltime=0;

			File f=new File(file);
			int rownumber=0;//行数
			if(f.exists()){//判断文件是否存在
				try {
					FileReader fr=new FileReader(f);
					LineNumberReader lnr=new LineNumberReader(fr);
					
					try {
						while(lnr.readLine()!=null){
							rownumber++;
						}
						lnr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("总行数为"+rownumber);
					long endTime=System.currentTimeMillis();
					totaltime=endTime-startTime;
					System.out.println("耗时："+totaltime);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				System.out.println("找不到文件");
			}
			
			request.setAttribute("rownumber", rownumber);
			request.setAttribute("file", f);
			request.setAttribute("totaltime", totaltime);
			request.getRequestDispatcher("/html/countRowNumber.jsp").forward(request, response);
	
	}

		/**
		 * 统计给定高频单词个数及其柱状图
		 * 有问题
		 * @param request
		 * @param response
		 * @throws IOException 
		 * @throws ServletException 
		 */
		private void showHistogram(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			long startTime=System.currentTimeMillis();
			long totaltime=0;
			
			Count1 c1=new Count1();
			c1.wcount(file);
			
			List<Map.Entry<String, Integer>> ordlist = new ArrayList<Map.Entry<String,Integer>>(c1.map.entrySet());
			//排序
			Collections.sort(ordlist,c1.valcom);
			
			Integer k=Integer.parseInt(request.getParameter("wordnumber"));
			int num=k;
			 Map<String,Integer> map = new HashMap<String, Integer>();
			if(k>0&&k<=ordlist.size())
			 {		
				for (Map.Entry<String, Integer> entry : ordlist) {
					
					map.put(entry.getKey(), entry.getValue());
					
					if(--k==0)
						break;
				}
			   }else{
			       	System.out.println("输入有误！请重新输入！");
			  }
			long endTime=System.currentTimeMillis();
			totaltime=endTime-startTime;
			
			request.setAttribute("totaltime", totaltime);
			request.setAttribute("ordlist", map);
			request.setAttribute("k", num);
			request.setAttribute("file", file);
			request.getRequestDispatcher("/html/HighFrequencyWordsAndHistogram.jsp").forward(request, response);
		
	}
		/**
		 * 统计给定单词词频
		 * @param request
		 * @param response
		 * @throws IOException 
		 * @throws ServletException 
		 */
		private void givenWordCount(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			Count1 c1=new Count1();
			c1.wcount(file);
			
			List<Map.Entry<String, Integer>> ordlist = new ArrayList<Map.Entry<String,Integer>>(c1.map.entrySet());
			//排序
			Collections.sort(ordlist,c1.valcom);	
		    
			String givenword=request.getParameter("givenword");
			
			String word="";
			int count=0;
			int flag=1;
			
			//查询统计给定单词词频
			for (Map.Entry<String, Integer> entry : ordlist) {
				
				if(entry.getKey().equals(givenword)){
					word=entry.getKey();
					count=entry.getValue();
					flag=1;
					break;
				}else{
					flag=0;
				}				
			}

			if(flag==1){
				System.out.println("该单词"+word+"出现的个数为:"+count);	
			}else{
				System.out.println("对不起文章中没有出现该单词！");
			}
			
			request.setAttribute("givenword", givenword);
			request.setAttribute("word", word);
			request.setAttribute("count", count);
			request.setAttribute("file", file);
			request.getRequestDispatcher("/html/givenWordCount.jsp").forward(request, response);
	}

		/**
		 * 获取文件路径，统计词频
		 * @param request
		 * @param response
		 * @throws IOException 
		 * @throws ServletException 
		 */
		private void getfilename(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			long startTime=System.currentTimeMillis();
			long totaltime=0;

			String filename=request.getParameter("filename");
			file=filename;
			Count1 c1=new Count1();
			c1.wcount(filename);
			
			//response.getWriter().println(filename);
			List<Map.Entry<String, Integer>> ordlist = new ArrayList<Map.Entry<String,Integer>>(c1.map.entrySet());
			//排序
			Collections.sort(ordlist,c1.valcom);
			
			long endTime=System.currentTimeMillis();
			totaltime=endTime-startTime;
			System.out.println("耗时："+totaltime);
			
			request.setAttribute("filename", filename);
			request.setAttribute("ordlistsize", ordlist.size());
			request.setAttribute("ordlist", ordlist);
			request.setAttribute("totaltime", totaltime);
			//System.out.println("共有单词："+ordlist.size()+"种不同的单词");
			
			request.getRequestDispatcher("/html/getWordCount.jsp").forward(request, response);
			
			//System.out.println("输入的文件路径为："+filename);
			
			
		}
		
}
	