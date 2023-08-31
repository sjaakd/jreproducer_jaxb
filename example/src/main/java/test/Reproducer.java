package test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import nl.broservices.xsd.cptcommon.v_1_1.ObjectFactory;

public class Reproducer {
    private static final ObjectFactory OF = new ObjectFactory();

    private static JAXBContext getJaxbContext() {
        try {
            return JAXBContext.newInstance( OF.getClass() );
        }
        catch ( JAXBException ex ) {
            throw new RuntimeException( ex );
        }
    }

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = getJaxbContext();
        context.createUnmarshaller();
        System.out.println( "finished" );

    }
}
