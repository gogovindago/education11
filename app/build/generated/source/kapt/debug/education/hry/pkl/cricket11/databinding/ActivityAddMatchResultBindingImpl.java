package education.hry.pkl.cricket11.databinding;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAddMatchResultBindingImpl extends ActivityAddMatchResultBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(36);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar_layout"},
            new int[] {1},
            new int[] {education.hry.pkl.cricket11.R.layout.toolbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.simpleSwipeRefreshLayout, 2);
        sViewsWithIds.put(R.id.edtMatchTitle, 3);
        sViewsWithIds.put(R.id.edtMatchDate, 4);
        sViewsWithIds.put(R.id.llteamdhe, 5);
        sViewsWithIds.put(R.id.txtteamdhe, 6);
        sViewsWithIds.put(R.id.spnteamdhe, 7);
        sViewsWithIds.put(R.id.edtdhescore, 8);
        sViewsWithIds.put(R.id.edtdheover, 9);
        sViewsWithIds.put(R.id.edtdheWicket, 10);
        sViewsWithIds.put(R.id.llOpponent, 11);
        sViewsWithIds.put(R.id.txtOpponent, 12);
        sViewsWithIds.put(R.id.spnOpponentteam, 13);
        sViewsWithIds.put(R.id.tlOpponentscore, 14);
        sViewsWithIds.put(R.id.edtOpponentscore, 15);
        sViewsWithIds.put(R.id.tlOpponentover, 16);
        sViewsWithIds.put(R.id.edtOpponentover, 17);
        sViewsWithIds.put(R.id.tlOpponentWicket, 18);
        sViewsWithIds.put(R.id.edtOpponentWicket, 19);
        sViewsWithIds.put(R.id.cardsforadoptedpersondetailrural, 20);
        sViewsWithIds.put(R.id.cardview, 21);
        sViewsWithIds.put(R.id.my_image_view, 22);
        sViewsWithIds.put(R.id.upload, 23);
        sViewsWithIds.put(R.id.txtupload, 24);
        sViewsWithIds.put(R.id.edtplayerName, 25);
        sViewsWithIds.put(R.id.llmomteamname, 26);
        sViewsWithIds.put(R.id.txtmomteamname, 27);
        sViewsWithIds.put(R.id.spnmomteamname, 28);
        sViewsWithIds.put(R.id.tlResultRemarks, 29);
        sViewsWithIds.put(R.id.edtResultRemarks, 30);
        sViewsWithIds.put(R.id.cardviewAssignment, 31);
        sViewsWithIds.put(R.id.llAssignment, 32);
        sViewsWithIds.put(R.id.txtAssignment, 33);
        sViewsWithIds.put(R.id.attachedpdfAssignment, 34);
        sViewsWithIds.put(R.id.btnaddmatchdetail, 35);
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAddMatchResultBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }
    private ActivityAddMatchResultBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageView) bindings[34]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[35]
            , (androidx.cardview.widget.CardView) bindings[20]
            , (androidx.cardview.widget.CardView) bindings[21]
            , (androidx.cardview.widget.CardView) bindings[31]
            , (com.google.android.material.textfield.TextInputEditText) bindings[4]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[19]
            , (com.google.android.material.textfield.TextInputEditText) bindings[17]
            , (com.google.android.material.textfield.TextInputEditText) bindings[15]
            , (com.google.android.material.textfield.TextInputEditText) bindings[30]
            , (com.google.android.material.textfield.TextInputEditText) bindings[10]
            , (com.google.android.material.textfield.TextInputEditText) bindings[9]
            , (com.google.android.material.textfield.TextInputEditText) bindings[8]
            , (com.google.android.material.textfield.TextInputEditText) bindings[25]
            , (android.widget.LinearLayout) bindings[32]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[5]
            , (com.facebook.drawee.view.SimpleDraweeView) bindings[22]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[2]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[13]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[28]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[7]
            , (com.google.android.material.textfield.TextInputLayout) bindings[18]
            , (com.google.android.material.textfield.TextInputLayout) bindings[16]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (com.google.android.material.textfield.TextInputLayout) bindings[29]
            , (education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding) bindings[1]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[24]
            , (android.widget.ImageView) bindings[23]
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