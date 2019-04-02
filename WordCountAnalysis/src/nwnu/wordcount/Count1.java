package nwnu.wordcount;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/*
 * @author guojia
 * 统计文件中单词出现的频数并输出到文件
 */
public class Count1 {
	
	 public Map<String,Integer> map = new HashMap<String, Integer>();
	
	public void readandprocessfile(String w){
		
		String filecontent = "";
    
        try {
            //读取指定文件DATA.txt,这里使用的是相对路径
        	FileInputStream fis = new FileInputStream(w);
        	//创建BufferedReader的缓冲流，一次性读取很多数据，然后按要求分次交给上层调用者
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
        //arrspilt数组用来统计单词出现的次数
        String [] arrspilt = filecontent.split("[^a-zA-Z0-9]+");
        for (int i=0;i<arrspilt.length;i++){

            //通过键名来获取键值
            if (map.get(arrspilt[i]) == null){
            	//统计键值对
                map.put(arrspilt[i],1);
            }else {
                int count = map.get(arrspilt[i]);
                map.put(arrspilt[i],++count);
            }
        }
       
          
	}
	//利用TreeMap实现Comparator接口
    public Comparator<Map.Entry<String, Integer>> valcom = new Comparator<Map.Entry<String,Integer>>() {

    	//对词频统计结果进行排序
        public int compare(Map.Entry<String, Integer> rst1,Map.Entry<String, Integer> rst2) {
        	
			//降序排序
        	int sortrst=rst2.getValue()-rst1.getValue();
			return sortrst;
			
        	} 
        
        };
	
	
	// 统计文件中单词出现的频数
	public void wcount(String w){
				
				readandprocessfile(w);
				
				List<Map.Entry<String, Integer>> ordlist = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
				
				// 用sort函数来排序
				Collections.sort(ordlist,valcom);
			
				//将词频统计结果输出到指定文件results.txt中
				try {
					FileWriter fw= new FileWriter("J:\\计算机科学与技术\\02 第二学期\\2  软件工程（代祖华）\\实验二 词频统计\\WordCount\\WordCountAnalysis\\src\\results.txt");
					BufferedWriter bw= new BufferedWriter(fw); 
					for (Map.Entry<String, Integer> entry : ordlist) {
						
						bw.write(entry.getKey() + ":" + entry.getValue());	
						bw.newLine();
					}
					bw.flush();
					bw.close();
					System.out.println("词频统计结果已输出到results.txt文件！");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
}





















