package pl.hostingasp.karpiq.asmx_connector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by karpi on 09.11.2015.
 */
public class ASMX_Actions {

    public final String WSDL_TARGET_NAMESPACE = "http://lokalizacja.karpiq.hostingasp.pl";
    public final String SOAP_ADDRESS = "http://lokalizacja.karpiq.hostingasp.pl/LokalizacjaWebService.asmx";

    private static ASMX_Actions ourInstance = new ASMX_Actions();

    public static ASMX_Actions getInstance() {
        return ourInstance;
    }

    private ASMX_Actions() {
    }

    public String GetUserLogin(int userID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/GetUserLogin";
        final String OPERATION_NAME = "GetUserLogin";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("userID", userID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public String GetUserID(String login) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/GetUserID";
        final String OPERATION_NAME = "GetUserID";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("login", login);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public String SignIn(String login, String password) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/SignIn";
        final String OPERATION_NAME = "SignIn";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("login", login);
            request.addProperty("password", password);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public String RegisterAccount(String login, String password, boolean isGuardian) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/RegisterAccount";
        final String OPERATION_NAME = "RegisterAccount";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("login", login);
            request.addProperty("password", password);
            request.addProperty("isGuardian", isGuardian);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public String InitiateConnection(int patientID, int guardianID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/InitiateConnection";
        final String OPERATION_NAME = "InitiateConnection";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("patientID", patientID);
            request.addProperty("guardianID", guardianID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public String ConfirmConnection(int patientID, int guardianID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/ConfirmConnection";
        final String OPERATION_NAME = "ConfirmConnection";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("patientID", patientID);
            request.addProperty("guardianID", guardianID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public String CancelConnection(int patientID, int guardianID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/CancelConnection";
        final String OPERATION_NAME = "CancelConnection";
        Object response;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("patientID", patientID);
            request.addProperty("guardianID", guardianID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }

    public Vector<Position> GetPositions(int patientID, int limit) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/GetPositions";
        final String OPERATION_NAME = "GetPositions";
        SoapObject response;
        Vector<Position> positions = new Vector<Position>();
        try
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("patientID", patientID);
            request.addProperty("limit", limit);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (SoapObject)envelope.getResponse();

            for (int i = 0; i < response.getPropertyCount();i++)
            {
                SoapObject obj =(SoapObject) response.getProperty(i);
                Position p = new Position();
                p.latitude = Float.parseFloat(obj.getProperty(0).toString());
                p.longitude = Float.parseFloat(obj.getProperty(1).toString());

                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss");
                p.dateTime = dt.parse(obj.getProperty(2).toString());
                positions.add(p);
            }

        }
        catch (Exception e)
        {

        }

        return positions;
    }

    public Vector<Integer> GetConnectedPatiens(int guardianID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/GetConnectedPatiens";
        final String OPERATION_NAME = "GetConnectedPatiens";
        SoapObject response = null;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("guardianID", guardianID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (SoapObject)envelope.getResponse();

        } catch (Exception e) {
            //response = e.getMessage();
        }
        Vector<Integer> array = new Vector<Integer>();
        for (int i = 0; i < response.getPropertyCount();i++)
        {
            array.add(Integer.parseInt(response.getProperty(i).toString()));
        }
        return array;
    }

    public Vector<Integer> GetConnectedGuardians(int patientID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/GetConnectedGuardians";
        final String OPERATION_NAME = "GetConnectedGuardians";
        SoapObject response = null;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("patientID", patientID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (SoapObject)envelope.getResponse();

        } catch (Exception e) {
            //response = e.getMessage();
        }
        Vector<Integer> array = new Vector<Integer>();
        for (int i = 0; i < response.getPropertyCount();i++)
        {
            array.add(Integer.parseInt(response.getProperty(i).toString()));
        }
        return array;
    }

    public Vector<Integer> GetInitiatedPatiens(int guardianID) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/GetInitiatedPatiens";
        final String OPERATION_NAME = "GetInitiatedPatiens";
        SoapObject response = null;
        try {

            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("guardianID", guardianID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (SoapObject)envelope.getResponse();

        } catch (Exception e) {
            //response = e.getMessage();
        }
        Vector<Integer> array = new Vector<Integer>();
        for (int i = 0; i < response.getPropertyCount();i++)
        {
            array.add(Integer.parseInt(response.getProperty(i).toString()));
        }
        return array;
    }

    public String AddPosition(int patientID, float latitude, float longitude, Date dateTime) {
        final String SOAP_ACTION = "http://lokalizacja.karpiq.hostingasp.pl/AddPosition";
        final String OPERATION_NAME = "AddPosition";
        Object response;
        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalFloat().register(envelope);
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

            request.addProperty("patientID", patientID);
            request.addProperty("latitude", latitude);
            request.addProperty("longitude", longitude);

            String lFormatTemplate = "yyyy-MM-dd'T'kk:mm:ss'Z'";
            DateFormat lDateFormat = new SimpleDateFormat(lFormatTemplate);
            String lDate = lDateFormat.format(dateTime);

            request.addProperty("dateTime", lDate);


            envelope.dotNet=true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_ADDRESS);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            response = (Object)envelope.getResponse();

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response.toString();
    }
}
