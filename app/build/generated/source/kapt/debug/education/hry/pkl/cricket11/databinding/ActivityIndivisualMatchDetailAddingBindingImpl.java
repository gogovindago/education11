package education.hry.pkl.cricket11.databinding;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityIndivisualMatchDetailAddingBindingImpl extends ActivityIndivisualMatchDetailAddingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(27);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar_layout"},
            new int[] {1},
            new int[] {education.hry.pkl.cricket11.R.layout.toolbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.simpleSwipeRefreshLayout, 2);
        sViewsWithIds.put(R.id.edtMatchDate, 3);
        sViewsWithIds.put(R.id.llteamhostdhe, 4);
        sViewsWithIds.put(R.id.txtteamhostdhe, 5);
        sViewsWithIds.put(R.id.spnteamdhe, 6);
        sViewsWithIds.put(R.id.llteamdhe, 7);
        sViewsWithIds.put(R.id.txtteamplayerdhe, 8);
        sViewsWithIds.put(R.id.spnteamplayerdhe, 9);
        sViewsWithIds.put(R.id.llOpponent, 10);
        sViewsWithIds.put(R.id.txtOpponent, 11);
        sViewsWithIds.put(R.id.spnOpponentteam, 12);
        sViewsWithIds.put(R.id.edtScored, 13);
        sViewsWithIds.put(R.id.edtBallFaced, 14);
        sViewsWithIds.put(R.id.edt4s, 15);
        sViewsWithIds.put(R.id.edt6s, 16);
        sViewsWithIds.put(R.id.btnRadiogroup, 17);
        sViewsWithIds.put(R.id.rdbOUt, 18);
        sViewsWithIds.put(R.id.rdbNotOUt, 19);
        sViewsWithIds.put(R.id.edtOverbowled, 20);
        sViewsWithIds.put(R.id.edtMaidenOver, 21);
        sViewsWithIds.put(R.id.edtRun, 22);
        sViewsWithIds.put(R.id.edtWicketTaken, 23);
        sViewsWithIds.put(R.id.edtdoneRunOut, 24);
        sViewsWithIds.put(R.id.edttakeCatch, 25);
        sViewsWithIds.put(R.id.btnaddmatchdetail, 26);
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityIndivisualMatchDetailAddingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }
    private ActivityIndivisualMatchDetailAddingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.RadioGroup) bindings[17]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[26]
            , (com.google.android.material.textfield.TextInputEditText) bindings[15]
            , (com.google.android.material.textfield.TextInputEditText) bindings[16]
            , (com.google.android.material.textfield.TextInputEditText) bindings[14]
            , (com.google.android.material.textfield.TextInputEditText) bindings[21]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[20]
            , (com.google.android.material.textfield.TextInputEditText) bindings[22]
            , (com.google.android.material.textfield.TextInputEditText) bindings[13]
            , (com.google.android.material.textfield.TextInputEditText) bindings[23]
            , (com.google.android.material.textfield.TextInputEditText) bindings[24]
            , (com.google.android.material.textfield.TextInputEditText) bindings[25]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.RadioButton) bindings[19]
            , (android.widget.RadioButton) bindings[18]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[2]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[12]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[6]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[9]
            , (education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding) bindings[1]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            );
        this.mboundView0 = (androidx.appcompat.widget.LinearLayoutCompat) bindings[0];
        this.mboundView0.setTag(null);
        setContainedBinding(this.toolbar);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        toolbar.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (toolbar.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        toolbar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeToolbar((education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeToolbar(education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding Toolbar, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(toolbar);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): toolbar
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}