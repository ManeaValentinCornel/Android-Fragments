package vcmanea.example.f04_onbackpressed_findfragmentbyid_removefragment_replacefragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Button mButton;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mTextView=findViewById(R.id.textView);
        mButton=findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();

            }
        });

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                mTextView.setText("Fragment count in the backstack is :" + mFragmentManager.getBackStackEntryCount());
            }
        });

        }


    private void addFragment() {
        Fragment fragment=mFragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment instanceof FragmentOne){
            fragment=new FragmentTwo();
        }

        else if(fragment instanceof FragmentTwo){
            fragment=new FragmentThree();

        }
        else{
            fragment=new FragmentOne();

        }
        
        mFragmentTransaction=mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container,fragment);
//      mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();

        }


    @Override
    public void onBackPressed() {
        Fragment fragment=mFragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment!=null){
            mFragmentTransaction=mFragmentManager.beginTransaction();
            mFragmentTransaction.remove(fragment);
            mFragmentTransaction.commit();
        }
        else {
            super.onBackPressed();
        }

    }
}



