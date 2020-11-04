package pt.ipbeja.pdm.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.pdm.contacts.database.ContactDatabase;

public class MainActivity extends AppCompatActivity {


    private RecyclerView contactList;
    private FloatingActionButton createContactBtn;

    private ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.contactList = findViewById(R.id.contact_list);

        this.createContactBtn = findViewById(R.id.create_contact_btn);
        this.createContactBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateContactActivity.class);
            startActivity(intent);
        });

        this.adapter = new ContactAdapter();
        contactList.setAdapter(adapter);

    }


    @Override
    protected void onStart() {
        super.onStart();

        List<Contact> all = ContactDatabase.getInstance(getApplicationContext())
                .contactDao()
                .getAll();
        adapter.setContacts(all);

    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

        // Ver https://github.com/diogopm-ipbeja/pdm_android_lists

        private List<Contact> data = new ArrayList<>();


        public void setContacts(List<Contact> contacts) {
            this.data = contacts;
            notifyDataSetChanged();
        }

        public void addContact(Contact c) {
            this.data.add(c);
            // Quando alteramos os dados da lista, temos de notificar o adapter que os mesmos mudaram
            // Se não invocar um notify(...) as alterações não se reflectem na RecyclerView.
            notifyDataSetChanged();
            // notifyItemInserted(data.size() - 1); // Podemos usar uma notificação mais específica
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contact c = data.get(position);
            holder.bind(c);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }


    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {

        // Ver https://github.com/diogopm-ipbeja/pdm_android_lists

        private Contact contact;

        private final ImageView imageView;
        private final TextView nameField;
        private final TextView userNameField;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ter em atenção que deve procurar pelas views do layout na View recebida (itemView)
            this.imageView = itemView.findViewById(R.id.imageView);
            this.nameField = itemView.findViewById(R.id.contact_name);
            this.userNameField = itemView.findViewById(R.id.contact_username);

            itemView.setOnClickListener(v -> {
                goToInstagramProfile(contact.getUsername());
            });

            this.imageView.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, contact.toString(), Toast.LENGTH_SHORT).show();
            });

        }

        public void bind(Contact contact) {
            // Para melhor estruturar o código é ideal criar um método que faz a ligação dos dados à View no próprio ViewHolder
            // Deve passar como parâmetro o modelo de dados para este item da lista (Contact neste caso)

            this.contact = contact; // Opcionalmente também o pode guardar como atributo

            // Aqui coloca os dados nas Views respectivas
            this.nameField.setText(contact.getName());
            this.userNameField.setText(contact.getUsername());
        }

        private void goToInstagramProfile(String igUser) {
            // Ver projeto Intents - https://github.com/diogopm-ipbeja/pdm_android_intents
            Uri uri;
            try {
                getPackageManager().getApplicationInfo("com.instagram.android", 0);
                uri = Uri.parse("https://instagram.com/_u/" + igUser);

            } catch (PackageManager.NameNotFoundException e) {
                uri = Uri.parse("https://instagram.com/" + igUser);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }


    }


}


























