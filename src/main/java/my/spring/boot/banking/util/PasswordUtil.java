package my.spring.boot.banking.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {

	private final static Logger LOG = Logger.getLogger(PasswordUtil.class.getName());
	private final static String FORMAT_HEXA = "%02x";
	private final static String  SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA512";
	private final static int HASH_KEY_LENGTH_VALUE = 256;
	private final static int HASH_ITERATIONS_VALUE = 1024;
	private final static byte[] HASH_SALT_BYTES = "##BNK@@27022018$$|T|Y|XMMWWoooQPQPQPQPoooWWMMX|Y|T|$$81022072@@KNB##".getBytes();
	
	
	public static String getHashedPassword(String password) 
	{
		String hashedPassword = null;
		try 
		{
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
			char[] passwordCharArray = password.toCharArray();
			PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordCharArray,HASH_SALT_BYTES, HASH_ITERATIONS_VALUE,HASH_KEY_LENGTH_VALUE);
			SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
			byte[] bytes = secretKey.getEncoded();
			hashedPassword = bytesTohexadecimal(bytes); 
		} 
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
		{
			LOG.log(Level.SEVERE, "Error while getting hashed password", e);
		}
		return hashedPassword;
	}

	
	public static String bytesTohexadecimal(byte[] bytes) 
	{
		Formatter formatter = new Formatter();
		for (byte b : bytes) 
		{
			formatter.format(FORMAT_HEXA, b);
		}
		String hexString = formatter.toString();
		formatter.close();
		return hexString;
	}
}
