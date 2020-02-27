package vcmanea.example.f05_popbackstack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Button mAdd;
    Button mPop;
    Button mRemove;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mTextView = findViewById(R.id.textView);

        mAdd = findViewById(R.id.add);
        mPop = findViewById(R.id.pop);
        mRemove = findViewById(R.id.remove);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();

            }
        });
        mPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment();

            }
        });
        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();

            }
        });

        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                mTextView.setText("Fragment count in the backstack is :" + mFragmentManager.getBackStackEntryCount());


                StringBuilder backStackEntryMessage = new StringBuilder("Current status of fragment transaction back stack: " + mFragmentManager.getBackStackEntryCount() + "\n");


                for (int index = mFragmentManager.getBackStackEntryCount() - 1; index >= 0; index--) {
                    FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(index);
                    backStackEntryMessage.append(entry.getName() + "\n");

                }

                Log.i(TAG, backStackEntryMessage.toString());


            }
        });

    }


    private void addFragment() {
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment instanceof FragmentOne) {
            fragment = new FragmentTwo();
        } else if (fragment instanceof FragmentTwo) {
            fragment = new FragmentThree();

        } else {
            fragment = new FragmentOne();

        }

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, fragment);
        mFragmentTransaction.addToBackStack("Replace" + fragment.toString());
        mFragmentTransaction.commit();

    }

    private void removeFragment() {
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.remove(fragment);
            mFragmentTransaction.addToBackStack("Remove" + fragment.toString());
            mFragmentTransaction.commit();
        }


    }



    private void popFragment(){
//        mFragmentManager.popBackStack();
        mFragmentManager.popBackStack(5,0); //Popup everything up to the index 5 which will be 6 backstack entries
        mFragmentManager.popBackStack(5,FragmentManager.POP_BACK_STACK_INCLUSIVE); //Popup everything up to the index 5 which will be 6 backstack entries including the entry with the id 5
    }
}




