//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.07.28 时间 02:09:58 PM CST 
//


package cn.com.cslc.aw.ws.client2.model.generate;
import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.com.cslc.aw.core.ws.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.com.cslc.aw.core.ws.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SynchronizeUserRequest }
     * 
     */
    public SynchronizeUserRequest createSynchronizeUserRequest() {
        return new SynchronizeUserRequest();
    }

    /**
     * Create an instance of {@link RequestHead }
     * 
     */
    public RequestHead createRequestHead() {
        return new RequestHead();
    }

    /**
     * Create an instance of {@link SynchronizeUserResponse }
     * 
     */
    public SynchronizeUserResponse createSynchronizeUserResponse() {
        return new SynchronizeUserResponse();
    }

    /**
     * Create an instance of {@link ResponseHead }
     * 
     */
    public ResponseHead createResponseHead() {
        return new ResponseHead();
    }

}
