package test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.example.MyTestResultType;
import org.example.ObjectFactory;

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

        // -- create object
        JAXBContext context = getJaxbContext();
        MyTestResultType result = OF.createMyTestResultType();
        MyTestResultType.Values values = OF.createMyTestResultTypeValues();
        values.getContent().add( "test" );
        result.setValues( values );
        JAXBElement<MyTestResultType> resultElemIn = OF.createMyTestResult( result );

        // -- marshall
        Marshaller marshaller = context.createMarshaller();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(  resultElemIn, os );

        // -- unmarshall
        ByteArrayInputStream is = new ByteArrayInputStream( os.toByteArray() );
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<MyTestResultType> resultElemOut = (JAXBElement<MyTestResultType>) unmarshaller.unmarshal( is );

        // -- print
        System.out.println( "finished: " + resultElemOut.getValue().getValues().getContent().get( 0 ) );

    }
}
