package com.zlf.reqBucket.restAPI;
/**
 * Amazon S3 支持在所有区域进行虚拟托管式和路径式访问。
 * 但是，路径式语法要求您在尝试访问存储段时必须使用特定于地区的终端节点。
 * @author Administrator
 * 
 *注意，无论存储桶位于哪个区域，您都可使用美国标准终端节点[s3.amazonaws.com]，而不是区域特定的终端节点。


 */
public class RestApi {
	public static void main(String[] args) {
		System.out.println("---------使用美国标准终端节点--------------");
//---路径式访问   使用 johnsmith.net 作为存储桶名称，使用 homepage.html 作为密钥名称。--//
		//URL 
		//	http://s3.amazonaws.com/johnsmith.net/homepage.html
		//请求如下：
		//GET /johnsmith.net/homepage.html HTTP/1.1
		//Host: s3.amazonaws.com
//---路径式访问   使用 johnsmith.net 作为存储桶名称，使用 homepage.html 作为密钥名称。--//
	
		
//---例 虚拟托管式方法--johnsmith.net 作为存储桶名称，使用 homepage.html 作为密钥名称.----//		
	//URL 如下：
	//http://johnsmith.net.s3.amazonaws.com/homepage.html
		
	//请求如下：	
	//GET /homepage.html HTTP/1.1
	//Host: johnsmith.net.s3.amazonaws.com
//-----例 虚拟托管式方法--johnsmith.net 作为存储桶名称，使用 homepage.html 作为密钥名称.----//		
		System.out.println("---------使用美国标准终端节点--------------");

		
		System.out.println("---------非美国标准终端节点，比如欧洲（爱尔兰） 区域中存储桶请求，也可以用上面的请求方式，也可以用下面的方式--------------");
//本示例使用 johnsmith.eu 作为 欧洲（爱尔兰） 区域中存储桶的名称，并使用 homepage.html 作为密钥名称。
	//URL 如下：
	//http://johnsmith.eu.s3-eu-west-1.amazonaws.com/homepage.html
	
	//请求如下：
	//	GET /homepage.html HTTP/1.1
	//	Host: johnsmith.eu.s3-eu-west-1.amazonaws.com
	//注意，无论存储桶位于哪个区域，您都可使用美国标准终端节点，而不是区域特定的终端节点。
		//URL   http://johnsmith.eu.s3.amazonaws.com/homepage.html
		//请求如下：
		//GET /homepage.html HTTP/1.1
		//Host: johnsmith.eu.s3.amazonaws.com
		System.out.println("---------非美国标准终端节点，比如欧洲（爱尔兰） 区域中存储桶请求，也可以用上面的请求方式，也可以用下面的方式--------------");
	}
}
