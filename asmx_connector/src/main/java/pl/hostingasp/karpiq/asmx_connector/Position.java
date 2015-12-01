package pl.hostingasp.karpiq.asmx_connector;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by karpi on 09.11.2015.
 */
public class Position implements KvmSerializable
{
    public float latitude;
    public float longitude;
    public Date dateTime;

    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return latitude;
            case 1:
                return longitude;
            case 2:
                return dateTime;
        }

        return null;
    }

    public int getPropertyCount() {
        return 3;
    }

    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
            case 0:
                info.type = float.class;
                info.name = "latitude";
                break;
            case 1:
                info.type = float.class;
                info.name = "longitude";
                break;
            case 2:
                info.type = Date.class;
                info.name = "dateTime";
                break;
            default:break;
        }
    }

    public void setProperty(int index, Object value) {
        switch(index)
        {
            case 0:
                latitude =(float) value;
                break;
            case 1:
                longitude =(float) value;
                break;
            case 2:
                dateTime = (Date) value;
                break;
            default:
                break;
        }
    }

    public void setInnerText(String s) {
    }
    public String getInnerText() {
        return "lol";
    }
}
