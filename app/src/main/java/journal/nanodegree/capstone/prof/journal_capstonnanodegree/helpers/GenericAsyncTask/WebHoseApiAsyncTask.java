package journal.nanodegree.capstone.prof.journal_capstonnanodegree.helpers.GenericAsyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import journal.nanodegree.capstone.prof.journal_capstonnanodegree.R;
import journal.nanodegree.capstone.prof.journal_capstonnanodegree.helpers.OptionsEntity;

/**
 * Created by Prof-Mohamed Atef on 1/10/2019.
 */

public class WebHoseApiAsyncTask extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

    private final String LOG_TAG = WebHoseApiAsyncTask.class.getSimpleName();

    private ProgressDialog dialog;
    public JSONObject ArticlesJson;
    public JSONArray ArticlesDataArray;
    public JSONObject oneArticleData;
    private ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    private String MAIN_LIST="posts";
    private String THREAD_="thread";
    private String SITE="site";
    private String SECTION_TITLE="section_title";
    private String TITLE="title";
    private String TITLE_FULL="title_full";
    private String PUBLISHED="published";
    private String MAIN_IMAGE="main_image";
    private String URL_="url";
    private String AUTHOR="author";
    private String Language="language";
    private String AUTHOR_STR;
    private String URL_STR;
    private String LANGUAGE_STR;
    private String SITE_STR;
    private String SECTIONTITLE_STR;
    private String TITLE_STR;
    private String TITLEFULL_STR;
    private String PUBLISHED_STR;
    private String MAINIMAGE_STR;
    private OptionsEntity optionsEntity;
    private String TEXT="text";
    private String TEXT_STR;


    @Override
    protected ArrayList<OptionsEntity> doInBackground(String... params) {
        String Articles_JsonSTR = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        if (params.length == 0) {
            return null;
        }

        try {

            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Articles_JsonSTR  = null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }

            Articles_JsonSTR = buffer.toString();

            Log.v(LOG_TAG, "Articles JSON String: " + Articles_JsonSTR );
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error here Exactly ", e);

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getArticlesJson(Articles_JsonSTR );
        } catch (JSONException e) {
            Log.e(LOG_TAG, "didn't got Articles Data from getJsonData method", e);
            e.printStackTrace();
        }
        return null;

        /*CertificateFactory cf = null;

        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        InputStream caInput=null;
        InputStream is = null;
        InputStream inputStream = null;
//            is=getClass().getResourceAsStream("/raw/certificate.crt");

        InputStream path=null;
        try {
            path=mContext.getResources().openRawResource(R.raw.webhose_certificate);
        }catch (Exception e){
            path=null;
        }


        if (path==null){
            path=null;
        }else {
            caInput = new BufferedInputStream(path);
        }
        Certificate ca = null;
        try {
            try {
                ca = cf.generateCertificate(caInput);
            } catch (CertificateException e) {
                e.printStackTrace();
            }
            Log.v(LOG_TAG, "my Certificate Authority= " + ((X509Certificate) ca).getSubjectDN());
        } finally {
            try {
                caInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

// Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(keyStoreType);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            keyStore.load(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        try {
            keyStore.setCertificateEntry("ca", ca);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }


// Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = null;
        try {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            tmf.init(keyStore);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

// Create an SSLContext that uses our TrustManager
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            context.init(null, tmf.getTrustManagers(), null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

// Tell the URLConnection to use a SocketFactory from our SSLContext
        URL url = null;
        BufferedReader reader = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        if (params.length == 0) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.setSSLSocketFactory(context.getSocketFactory());
        try {
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            copyInputStreamToOutputStream(in, System.out);
            /*
            End of SSL
             */

/*
        String UsersDesires_JsonSTR = null;

//            HttpURLConnection urlConnection = null;

        try {
//                URL url = new URL(params[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();


            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                UsersDesires_JsonSTR = null;
            }else {
                reader = new BufferedReader(new InputStreamReader(inputStream));
            }
            String line;
            if (reader!=null){
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
            }
            if (buffer.length() == 0) {
                return null;
            }
            UsersDesires_JsonSTR = buffer.toString();
            Log.v(LOG_TAG, "Articles String: " + UsersDesires_JsonSTR);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error here Exactly ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getArticlesJson(UsersDesires_JsonSTR);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "didn't got Users Desires from getJsonData method", e);
            e.printStackTrace();
        }
        return null;*/
    }

    private ArrayList<OptionsEntity> getArticlesJson(String articles_jsonSTR) throws JSONException {
        ArticlesJson = new JSONObject(articles_jsonSTR );
        ArticlesDataArray= ArticlesJson.getJSONArray(MAIN_LIST);
        list.clear();
        for (int i = 0; i < ArticlesDataArray.length(); i++) {
            oneArticleData = ArticlesDataArray.getJSONObject(i);
            URL_STR = oneArticleData.getString(URL_);
            AUTHOR_STR = oneArticleData.getString(AUTHOR);
            LANGUAGE_STR = oneArticleData.getString(Language);
            TEXT_STR = oneArticleData.getString(TEXT);
            JSONObject THREADJsonObj = oneArticleData.getJSONObject(THREAD_);
            SITE_STR = THREADJsonObj.getString(SITE);
            SECTIONTITLE_STR = THREADJsonObj.getString(SECTION_TITLE);
            TITLE_STR = THREADJsonObj.getString(TITLE);
            TITLEFULL_STR = THREADJsonObj.getString(TITLE_FULL);
            PUBLISHED_STR = THREADJsonObj.getString(PUBLISHED);
            MAINIMAGE_STR = THREADJsonObj.getString(MAIN_IMAGE);

            if (AUTHOR_STR==null){
                AUTHOR_STR="";
            }
            if (LANGUAGE_STR==null){
                LANGUAGE_STR="";
            }
            if (SITE_STR==null){
                SITE_STR="";
            }
            if (SECTIONTITLE_STR==null){
                SECTIONTITLE_STR="";
            }
            if (TITLE_STR==null){
                TITLE_STR="";
            }
            if (TITLEFULL_STR==null){
                TITLEFULL_STR="";
            }
            if (PUBLISHED_STR==null){
                PUBLISHED_STR="";
            }
            if (MAINIMAGE_STR==null){
                MAINIMAGE_STR="";
            }
            optionsEntity = new OptionsEntity(AUTHOR_STR, URL_STR, LANGUAGE_STR, SITE_STR, SECTIONTITLE_STR, TITLE_STR, TITLEFULL_STR, PUBLISHED_STR, MAINIMAGE_STR,TEXT_STR);
            list.add(optionsEntity);
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<OptionsEntity> result) {
        super.onPostExecute(result);
        if (result!=null){
            onWebHoseTaskCompleted.onWebHoseTaskCompleted(result);
            if (dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }

    public OnWebHoseTaskCompleted onWebHoseTaskCompleted;
    Context mContext;

    public WebHoseApiAsyncTask(OnWebHoseTaskCompleted onWebHoseTaskCompleted, Context mContext) {
        this.onWebHoseTaskCompleted = onWebHoseTaskCompleted;
        this.mContext = mContext;
        dialog=new ProgressDialog(mContext);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try{
            if (dialog!=null&&dialog.isShowing()){
                this.dialog.dismiss();
            }else {
                this.dialog.setMessage(mContext.getResources().getString(R.string.loading));
                this.dialog.show();
            }
        }catch (Exception e){
            Log.v(LOG_TAG, "Problem in ProgressDialogue" );
        }
    }

    public interface OnWebHoseTaskCompleted{
        void onWebHoseTaskCompleted(ArrayList<OptionsEntity> result);
    }
}