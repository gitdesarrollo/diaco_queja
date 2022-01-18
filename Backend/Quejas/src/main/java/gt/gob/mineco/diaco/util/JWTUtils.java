package gt.gob.mineco.diaco.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.commons.codec.binary.Base64;

public class JWTUtils {
	public static String generateToken(String subject, Map<String, Object> claims, long ttlMillis) {
		try {
			Builder builder = JWT.create();
			
			if(ttlMillis >= 0) {
				long nowMillis = System.currentTimeMillis();
				Date now = new Date(nowMillis);
				long expMillis = nowMillis + ttlMillis;
				Date exp = new Date(expMillis);
				builder.withExpiresAt(exp);
				builder.withSubject(subject);
				
				for(Map.Entry<String, Object> claim : claims.entrySet()) {
					builder.withClaim(claim.getKey(), claim.getValue());
				}
			}
			
			String token = builder.sign(Algorithm.HMAC256("MINECO#diaco"));

			return token;
		} catch(JWTCreationException | IllegalArgumentException | UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static String decodeToken(String token) {
		try {
			JWT jwt = JWT.decode(token);
//			System.out.println("\t" + jwt.getSubject());
//                        System.out.println("\t" + jwt.getClaim("username").asString());
//			System.out.println("\t" + jwt.getClaim("name").asString());
//			System.out.println("\t" + jwt.getClaim("admin").asBoolean());
//			System.out.println("\t" + jwt.getClaim("roles").asString());
			return jwt.toString();
		} catch(JWTDecodeException e) {
			e.printStackTrace();
			return "";
		}
	}
        
        public static String getUserName(String token) {
		try {
			JWT jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		} catch(JWTDecodeException e) {
			e.printStackTrace();
			return "";
		}
	}
        
        public static String getRolesUser(String token) {
		try {
                        System.out.println("-------------------before decode");
			/*JWT jwt = JWT.decode(token);
                        System.out.println("-------------------after decode");
                        System.out.println(jwt.getId());
                        System.out.println(jwt.toString());
			return jwt.getClaim("roles").asString();*/
                        String[] split_string = token.split("\\.");
                        String base64EncodedHeader = split_string[0];
                        String base64EncodedBody = split_string[1];
                        String base64EncodedSignature = split_string[2];
                        Base64 base64Url = new Base64(true);
                        String body = new String(base64Url.decode(base64EncodedBody));
                        System.out.println("JWT Body : "+body);        
                        String translation = body.toString();
                        System.out.println(translation);
                        return translation;
		} catch(JWTDecodeException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void verifyToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
	    JWTVerifier verifier = JWT.require(Algorithm.HMAC256("MINECO#diaco"))
	        .build(); //Reusable verifier instance
	    verifier.verify(token);
	}
	
}
