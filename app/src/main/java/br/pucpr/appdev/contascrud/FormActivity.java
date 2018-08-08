package br.pucpr.appdev.contascrud;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FormActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        String username = getIntent().getStringExtra("user_name");
        TextView labelForm = findViewById(R.id.labelForm);
        String lblFormText = String.format(getResources().getString(R.string.greetings_user), username);

        labelForm.setText(lblFormText);
    }

}
