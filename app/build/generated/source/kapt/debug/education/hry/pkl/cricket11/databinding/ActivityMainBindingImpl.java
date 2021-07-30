package education.hry.pkl.cricket11.databinding;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.simpleSwipeRefreshLayout, 1);
        sViewsWithIds.put(R.id.appbar, 2);
        sViewsWithIds.put(R.id.toolbar, 3);
        sViewsWithIds.put(R.id.toggle, 4);
        sViewsWithIds.put(R.id.logo, 5);
        sViewsWithIds.put(R.id.toolbartxt, 6);
        sViewsWithIds.put(R.id.content_frame, 7);
        sViewsWithIds.put(R.id.imageSlider, 8);
        sViewsWithIds.put(R.id.txtAdmin, 9);
        sViewsWithIds.put(R.id.rvadminimage, 10);
        sViewsWithIds.put(R.id.txtrecentmatch, 11);
        sViewsWithIds.put(R.id.rvrecentmatch, 12);
        sViewsWithIds.put(R.id.txtvideos, 13);
        sViewsWithIds.put(R.id.rvTopBattingAvg, 14);
        sViewsWithIds.put(R.id.txtTopBowlingAverage, 15);
        sViewsWithIds.put(R.id.recyclerView, 16);
        sViewsWithIds.put(R.id.txtmostrun, 17);
        sViewsWithIds.put(R.id.rvRun, 18);
        sViewsWithIds.put(R.id.txtwicketMost, 19);
        sViewsWithIds.put(R.id.rvWicketMost, 20);
        sViewsWithIds.put(R.id.txtMOstSixes, 21);
        sViewsWithIds.put(R.id.rvMostSixes, 22);
        sViewsWithIds.put(R.id.txtmostFours, 23);
        sViewsWithIds.put(R.id.rvMostFour, 24);
        sViewsWithIds.put(R.id.message, 25);
        sViewsWithIds.put(R.id.nav_view, 26);
        sViewsWithIds.put(R.id.materialCardView, 27);
        sViewsWithIds.put(R.id.profile_image, 28);
        sViewsWithIds.put(R.id.uname, 29);
        sViewsWithIds.put(R.id.umobile, 30);
        sViewsWithIds.put(R.id.uemailId, 31);
        sViewsWithIds.put(R.id.left_drawer, 32);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[2]
            , (android.widget.FrameLayout) bindings[7]
            , (androidx.drawerlayout.widget.DrawerLayout) bindings[0]
            , (com.smarteist.autoimageslider.SliderView) bindings[8]
            , (android.widget.ListView) bindings[32]
            , (android.widget.ImageView) bindings[5]
            , (com.google.android.material.card.MaterialCardView) bindings[27]
            , (android.widget.TextView) bindings[25]
            , (com.google.android.material.navigation.NavigationView) bindings[26]
            , (android.widget.ImageView) bindings[28]
            , (androidx.recyclerview.widget.RecyclerView) bindings[16]
            , (androidx.recyclerview.widget.RecyclerView) bindings[24]
            , (androidx.recyclerview.widget.RecyclerView) bindings[22]
            , (androidx.recyclerview.widget.RecyclerView) bindings[18]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (androidx.recyclerview.widget.RecyclerView) bindings[20]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (androidx.recyclerview.widget.RecyclerView) bindings[12]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[1]
            , (android.widget.ImageView) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[21]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[15]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[23]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[17]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[19]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[29]
            );
        this.drawerLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}