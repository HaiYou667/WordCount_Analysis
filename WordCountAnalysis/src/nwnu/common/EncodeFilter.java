package nwnu.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class EncodeFilter
 */

public class EncodeFilter implements Filter {

	private String encode="utf-8";//Ĭ�ϱ��뷽ʽΪutf-8
	private String contenttype="text/html";//Ĭ�϶���������text/html
	
	

	/**
	 * �����߼��ķ���
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/*
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");*/
		
		//System.out.println("������ִ��");
		
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		response.setContentType(contenttype);
		
		//��һ������
		chain.doFilter(request, response);
	}

	/**
	 *��ʼ������
	 * ִֻ��һ�Σ��ڷ�����������ʱ���ִ���ˡ�
	 * filterҲ��һ�������ࡣ
	 */
	//int index=0;
	public void init(FilterConfig fConfig) throws ServletException {
		//index++;
		//System.out.println(index);//������֤filter�Ƿ��ǵ�����
		
		//System.out.println("��ʼ��������");
	
		encode=fConfig.getInitParameter("encode");
		contenttype=fConfig.getInitParameter("contenttype");
		
		
	}


}
