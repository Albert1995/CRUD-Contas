package br.pucpr.appdev.contascrud.dao;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class DAOUtil {

    public static final String DB_FILE = "db.dat";

    public static Reader openFileToRead(Context ctx) throws FileNotFoundException {
        InputStream in = ctx.openFileInput(DB_FILE);
        InputStreamReader isr = new InputStreamReader(in);

        return isr;
    }

    public static Writer openFileToWrite(Context ctx) throws FileNotFoundException {
        OutputStream out = ctx.openFileOutput(DB_FILE, Context.MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(out);

        return osw;
    }
}
