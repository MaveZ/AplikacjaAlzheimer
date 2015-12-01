package pl.hostingasp.karpiq.asmx_connector;

import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by karpi on 09.11.2015.
 */
public class MarshalFloat implements Marshal
{
    @Override
    public Object readInstance(XmlPullParser parser, String namespace, String name,
                               PropertyInfo expected) throws IOException, XmlPullParserException {

        return Float.parseFloat(parser.nextText());
    }

    public void register(SoapSerializationEnvelope cm) {
        cm.addMapping(cm.xsd, "float", Float.class, this);

    }

    @Override
    public void writeInstance(XmlSerializer writer, Object obj) throws IOException {
        writer.text(obj.toString());
    }
}
