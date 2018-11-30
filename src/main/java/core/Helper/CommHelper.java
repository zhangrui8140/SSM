package core.Helper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class CommHelper{

    private static final Logger logger = LoggerFactory.getLogger(CommHelper.class);

    private static ObjectMapper objectMapper=new ObjectMapper();

    public static <T> T DeSerialize(String json,Class<T> objClass) {
        Object obj=null;
        try {
            obj=objectMapper.readValue(json,TypeFactory.rawClass(objClass));

        }catch (JsonParseException e){
            logger.error("DeSerializeJsonParseException:",e);
        }
        catch (JsonMappingException e){
            logger.error("DeSerializeJsonMappingException:",e);
        }
        catch (IOException e){
            logger.error("DeSerializeIOException:",e);
        }
        return (T)obj;
    }

    public static String Serialize(Object obj) {
        Writer wt=new StringWriter();
        try {
            objectMapper.writeValue(wt,obj);
        }
        catch (JsonGenerationException e) {
            logger.error("SerializeJsonParseException:",e);
        } catch (JsonMappingException e) {
            logger.error("SerializeJsonParseException:",e);
        } catch (IOException e) {
            logger.error("SerializeJsonParseException:",e);
        }
        return wt.toString();
    }
}
