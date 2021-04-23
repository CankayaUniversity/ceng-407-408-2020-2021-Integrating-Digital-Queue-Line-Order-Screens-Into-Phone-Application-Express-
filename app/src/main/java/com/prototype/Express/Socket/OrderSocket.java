package com.prototype.Express.Socket;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.prototype.Express.Activity.BasketActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class OrderSocket extends AsyncTask<String, Void, Void>
{
    Socket socket;
    DataOutputStream dataOutputStream;
    PrintWriter printWriter;

    @Override
    protected Void doInBackground(String... voids)
    {
        String message = voids[0];

        try {
            socket = new Socket("104.248.207.133", 5000);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            socket.close();


        }catch (IOException error){
            error.printStackTrace();
        }
        return null;
    }
}
