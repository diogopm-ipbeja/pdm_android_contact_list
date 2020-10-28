package pt.ipbeja.pdm.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class CreateContactActivity extends AppCompatActivity {

    public static final String NAME_KEY = "name";
    public static final String USERNAME_KEY = "username";

    private TextInputEditText nameInput;
    private TextInputEditText instagramUserInput;
    private FloatingActionButton createUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Ver projeto Intents - https://github.com/diogopm-ipbeja/pdm_android_intents

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        this.nameInput = findViewById(R.id.name_input);
        this.instagramUserInput = findViewById(R.id.username_input);
        this.createUserBtn = findViewById(R.id.create_user_btn);

        this.createUserBtn.setOnClickListener(v -> {
            String name = this.nameInput.getText().toString();
            String user = this.instagramUserInput.getText().toString();

            Intent intent = new Intent();
            intent.putExtra(NAME_KEY, name);
            intent.putExtra(USERNAME_KEY, user);

            setResult(RESULT_OK, intent);
            finish();
        });


    }
}