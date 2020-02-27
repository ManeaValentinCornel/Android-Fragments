package vcmanea.example.f03_addtobackstack_addtobackstackchangelistener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    AppCompatTextView mBackStackFragmentCount;
    Button mButton;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBackStackFragmentCount = findViewById(R.id.fragment_backstack_count_text);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();
            }
        });

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                mBackStackFragmentCount.setText("Fragment cout in back stack: " + mFragmentManager.getBackStackEntryCount());
            }
        });


    }


    private void addFragment() {
        Fragment fragment;
        switch (mFragmentManager.getBackStackEntryCount()) {
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            case 2:
                fragment = new FragmentThree();
                break;
            default:
                fragment = new FragmentOne();
                break;

        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


}
