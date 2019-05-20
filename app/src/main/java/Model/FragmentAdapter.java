package Model;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.emporium.matchtrackerappv2.R;

import ViewModel.FragmentArena;
import ViewModel.FragmentModern;
import ViewModel.FragmentStandard;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public FragmentAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        this.context = context;
    }
    //determine the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentArena();
            case 1:
                return new FragmentStandard();
            case 2:
                return new FragmentModern();
        }
        return null;
    }
    //for the number of tabs
    //todo algorithm to have a dynamic amount of tab
    @Override
    public int getCount() {
        return 3;
    }
    //for the title of each tab

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return context.getString(R.string.Format_arena);
            case 1:
                return context.getString(R.string.Format_standard);
            case 2:
                return context.getString(R.string.Format_modern);
        }
        return null;
    }
}
