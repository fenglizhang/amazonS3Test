package com.zlf.reqBucket.AWSorIAM;

import java.io.IOException;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;

/**
 * 以下 Java 代码示例列出了特定存储桶中的对象密钥。为了展示这个过程，
 * 代码示例会为默认一小时的会话获取[临时安全证书]，然后使用这些证书向 Amazon S3 发送经身份验证的请求。
 * @author Administrator
 *
 */
public class IAMTemporaryCertificateRequestExample {
		private static String bucketName = "com.taikang.voiceRecord.bucket";

	    public static void main(String[] args) throws IOException {        
	        
	    	AWSSecurityTokenServiceClient stsClient = 
	                   new AWSSecurityTokenServiceClient(new ProfileCredentialsProvider());        
	        //
	        // Start a session.
	        GetSessionTokenRequest getSessionTokenRequest =  new GetSessionTokenRequest();

	        GetSessionTokenResult sessionTokenResult =
	        		stsClient.getSessionToken(getSessionTokenRequest);
	        //得到临时会话证书
	        Credentials sessionCredentials = sessionTokenResult.getCredentials();
	        System.out.println("Session Credentials: " + sessionCredentials.toString());
	  
	        
	        // Package the session credentials as a BasicSessionCredentials 
	        // object for an S3 client object to use.
	        //打包证书到 BasicSessionCredentials
	        BasicSessionCredentials basicSessionCredentials = 
	             new BasicSessionCredentials(sessionCredentials.getAccessKeyId(), 
	        		                         sessionCredentials.getSecretAccessKey(), 
	        		                         sessionCredentials.getSessionToken());
	        AmazonS3Client s3 = new AmazonS3Client(basicSessionCredentials);

	        // Test. For example, get object keys for a given bucket. 
	        ObjectListing objects = s3.listObjects(bucketName);
	        System.out.println("No. of Objects = " +objects.getObjectSummaries().size());
	    }
	
}
