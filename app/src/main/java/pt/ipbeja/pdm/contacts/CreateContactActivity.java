package pt.ipbeja.pdm.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import pt.ipbeja.pdm.contacts.database.ContactDatabase;

public class CreateContactActivity extends AppCompatActivity {

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

            Contact contact = new Contact(0, name, user);
            ContactDatabase.getInstance(getApplicationContext())
                    .contactDao()
                    .insert(contact);

            finish();
        });


    }
}