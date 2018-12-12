package com.zlf.reqBucket.AWSorIAM;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
/**
 * 使用 IAM [用户临时证书]创建请求 
 * 
 * IAM 用户或 AWS 账户可以使用AWS SDK for Java请求临时安全证书（请参阅创建请求），然后使用这些证书访问 Amazon S3。在会话持续时间结束后，
 * 这些证书将过期。默认情况下，会话的持续时间为一个小时。 如果您使用 IAM 用户证书，您可以在请求临时安全证书时，指定 1 到 36 小时的持续时间。
 * @author Administrator
 *
 */
public class IAMTemporaryCertificateRequestBucket {

	private final static String bucketName="com.taikang.voiceRecord.bucket";
	
	public static void main(String[] args) {
		// In real applications, the following code is part of your trusted code. It has 
		// your security credentials you use to obtain temporary security credentials.
		//@1.创建 AWS Security Token Service客户端 AWSSecurityTokenServiceClient 的实例。
		AWSSecurityTokenServiceClient stsClient = 
		           new AWSSecurityTokenServiceClient(new ProfileCredentialsProvider());
		        
		//@2.通过调用您在上一步骤中创建的 STS 客户端的 GetSessionToken 方法，开始会话。您可以使用 GetSessionTokenRequest 
		//对象向此方法提供会话信息。此方法将返回您的临时安全证书。
		// Manually start a session.
		GetSessionTokenRequest getSessionTokenRequest = new GetSessionTokenRequest();
		// Following duration can be set only if temporary credentials are requested by an IAM user.
		//可以设置临时会话有效时间，默认为一个小时
		getSessionTokenRequest.setDurationSeconds(7200); 

		GetSessionTokenResult sessionTokenResult = 
		                           stsClient.getSessionToken(getSessionTokenRequest);
		Credentials sessionCredentials = sessionTokenResult.getCredentials();
		  
		// Package the temporary security credentials as 
		// a BasicSessionCredentials object, for an Amazon S3 client object to use.
		//@3.将临时安全证书打包在 BasicSessionCredentials 对象的实例中，以便可以向 Amazon S3 客户端提供证书。
		BasicSessionCredentials basicSessionCredentials = 
		               new BasicSessionCredentials(sessionCredentials.getAccessKeyId(), 
		        		                           sessionCredentials.getSecretAccessKey(), 
		        		                            sessionCredentials.getSessionToken());

		// The following will be part of your less trusted code. You provide temporary security
		// credentials so it can send authenticated requests to Amazon S3. 
		// Create Amazon S3 client by passing in the basicSessionCredentials object.
		//@4.通过传入临时安全证书创建 AmazonS3Client 类的实例。
		AmazonS3Client s3 = new AmazonS3Client(basicSessionCredentials);
		            
		// Test. For example, get object keys in a bucket.
		ObjectListing objects = s3.listObjects(bucketName);
	}
}
