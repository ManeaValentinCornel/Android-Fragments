package vcmanea.example.f01_addingfragment_dynamically_programatically;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void addFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        SampleFragment sapleFragment=new SampleFragment();
        fragmentTransaction.add(R.id.fragmentContainer,sapleFragment);
        fragmentTransaction.commit();
    }

}
