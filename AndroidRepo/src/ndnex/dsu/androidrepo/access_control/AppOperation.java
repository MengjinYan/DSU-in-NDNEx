package ndnex.dsu.androidrepo.access_control;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import ndnex.dsu.androidrepo.db.DbContract.ACL;
import ndnex.dsu.androidrepo.db.DbManager;
import net.named_data.jndn.Interest;
import net.named_data.jndn.Name;
import net.named_data.jndn.Name.Component;
import net.named_data.jndn.security.OnVerifiedInterest;
import net.named_data.jndn.security.OnVerifyInterestFailed;
import android.database.Cursor;

public class AppOperation implements OnVerifiedInterest, OnVerifyInterestFailed {
	
	/*
	 * Acquire data from database
	 */
	public void aquireData(Interest interest) {		
		//verify the Interest
		//TODO: the security library is not finalized.
		try{
			//KeyChain keyChain = new KeyChain();
			//keyChain.verifyInterest(interest, this, this);
			
			// TODO: if we have decide the naming convention, we can get the appName from the interest name
			// TODO: I just simply designed the interest naming convention: 
			//		user-namespace/app-name/operation/signature
			//determine if the application is in the ACL table
			String appName = interest.getName().get(-3).toEscapedString();
			Cursor c = DbManager.readACL(appName);
			if (!c.moveToFirst())
				throw new Exception("The application is not registered.");
			
			//get the public key from the database
			//In this case the app should use pubKey.getEncoded() to get the bytes and store in the database
			byte[] publicKeyBytes = c.getBlob(c.getColumnIndexOrThrow(ACL.COLUMN_NAME_APP_PUBLIC_KEY));
			KeyFactory rsaKeyFactory = KeyFactory.getInstance("RSA"); 
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			RSAPublicKey pubKey = (RSAPublicKey)rsaKeyFactory.generatePublic(publicKeySpec);
			
			//get the signature and verify the signature
			//the signature is generated with the following steps:
			//encode the interest -> get digest using SHA256 -> sign with private key -> signature
			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initVerify(pubKey);
			Name rawName = interest.getName().getSubName(0, -1);
			Component nameSignature = interest.getName().get(-1);
			interest.setName(rawName);
			signature.update(interest.wireEncode().getImmutableArray());
			
			if(signature.verify(nameSignature.getValue().getImmutableArray()))
				onVerifiedInterest(interest);
			else
				onVerifyInterestFailed(interest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onVerifiedInterest(Interest interest) {
		String appName = interest.getName().get(-3).toEscapedString();
		Cursor c = DbManager.readACL(appName);
		try {
			
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		byte[] filter = c.getBlob(c.getColumnIndexOrThrow(ACL.COLUMN_NAME_APP_FILTER));
		
	}

	@Override
	public void onVerifyInterestFailed(Interest interest) {
		// TODO Auto-generated method stub
		System.out.print("Verify Interest Failed.");
	}

	
}
