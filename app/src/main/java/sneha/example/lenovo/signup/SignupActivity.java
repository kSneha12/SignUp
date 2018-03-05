package sneha.example.lenovo.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private Button b1;

    private RequestQueue RequestQueue;

    private StringRequest StringRequest;
    private String url = "http://yuvagen.com/android_map/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et1=(EditText)findViewById(R.id.username);
        et2=(EditText)findViewById(R.id.email);
        et3=(EditText)findViewById(R.id.pswd);
        et4=(EditText)findViewById(R.id.repswd);

        b1=(Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str=et1.getText().toString();
               if(str.isEmpty())
               {
                   et1.setError("Name is Empty");
               }
               else if(et2.getText().toString().isEmpty())
               {
                   et2.setError("Empty");
                }
                else if(et3.getText().toString().isEmpty())
                {
                   et3.setError("Empty");
                }else if(et4.getText().toString().isEmpty()){
                    et4.setError("Empty");
               }else if (!(et3.getText().toString().equals(et4.getText().toString()))){

                   et4.setError("Password miss mactch");
               }else{
                  signupApi(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
               }
            }
        });
    }

    private void signupApi(final String name, final String email, final String password) {
        final ProgressDialog progressDialog = ProgressDialog.show(this,"Loading...","Please wait...",false,false);
        //RequestQueue initialized
        RequestQueue = Volley.newRequestQueue(this);
        progressDialog.show();
        //String Request initialized
        StringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Showing toast message of the response
                        Log.e("responce", "--" + s);
                        progressDialog.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("responce", "--" + volleyError.getMessage());
                        Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String

                HashMap<String, String> params = new HashMap<String,String>();
                params.put("email", email);
                params.put("name", name);
                params.put("password",password);

                //returning parameters
                return params;
            }
        };


//Adding request to the queue
        RequestQueue.add(StringRequest);
        RequestQueue.start();
    }
}


