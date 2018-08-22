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

    public static Reader openFileToRead(String file, Context ctx) throws FileNotFoundException {
        InputStream in = ctx.openFileInput(file);
        InputStreamReader isr = new InputStreamReader(in);

        return isr;
    }

    public static Writer openFileToWrite(String file, Context ctx, int mode) throws FileNotFoundException {
        OutputStream out = ctx.openFileOutput(file, mode);
        OutputStreamWriter osw = new OutputStreamWriter(out);

        return osw;
    }
}
