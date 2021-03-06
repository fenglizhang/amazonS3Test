package com.zlf.bucket;

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;

/**
 * 创建存储桶的大方向有两个：
 * @1.按照服务器所在位置的显式指定 AWS 区域创建客户端，
 * @2.仅指定存储桶名称来发送创建存储桶请求。创建存储桶请求不指定其他 AWS 区域；
 * 因此，客户端向 Amazon S3 发送请求以在创建客户端时指定的区域中创建存储桶。
 * 
 * @author Administrator
 *
 */
public class CreateBucketDemo01 {
	
		private static String bucketName     = "com.taikang.voiceRecord";
		
		public static void main(String[] args) throws IOException {
	       
			AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
	        s3client.setRegion(Region.getRegion(Regions.US_WEST_1));
	       
	        try {
	            if(!(s3client.doesBucketExist(bucketName)))
	            {
	            	// Note that CreateBucketRequest does not specify region. So bucket is 
	            	// created in the region specified in the client.
	            	s3client.createBucket(new CreateBucketRequest(
							bucketName));
	            }
	            // Get location.
	            String bucketLocation = s3client.getBucketLocation(new GetBucketLocationRequest(bucketName));
	            System.out.println("bucket location = " + bucketLocation);

	         } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException, which " +
	            		"means your request made it " +
	                    "to Amazon S3, but was rejected with an error response" +
	                    " for some reason.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException, which " +
	            		"means the client encountered " +
	                    "an internal error while trying to " +
	                    "communicate with S3, " +
	                    "such as not being able to access the network.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
	    
	}
}
