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
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(23);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar_layout"},
            new int[] {1},
            new int[] {education.hry.pkl.cricket11.R.layout.toolbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.edtMatchTitle, 2);
        sViewsWithIds.put(R.id.edtMatchDate, 3);
        sViewsWithIds.put(R.id.llteamdhe, 4);
        sViewsWithIds.put(R.id.txtteamdhe, 5);
        sViewsWithIds.put(R.id.spnteamdhe, 6);
        sViewsWithIds.put(R.id.edtdhescore, 7);
        sViewsWithIds.put(R.id.edtdheover, 8);
        sViewsWithIds.put(R.id.llOpponent, 9);
        sViewsWithIds.put(R.id.txtOpponent, 10);
        sViewsWithIds.put(R.id.spnOpponentteam, 11);
        sViewsWithIds.put(R.id.edtOpponentscore, 12);
        sViewsWithIds.put(R.id.edtOpponentover, 13);
        sViewsWithIds.put(R.id.cardsforadoptedpersondetailrural, 14);
        sViewsWithIds.put(R.id.cardview, 15);
        sViewsWithIds.put(R.id.my_image_view, 16);
        sViewsWithIds.put(R.id.upload, 17);
        sViewsWithIds.put(R.id.txtupload, 18);
        sViewsWithIds.put(R.id.edtplayerName, 19);
        sViewsWithIds.put(R.id.llmomteamname, 20);
        sViewsWithIds.put(R.id.txtmomteamname, 21);
        sViewsWithIds.put(R.id.spnmomteamname, 22);
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAddMatchResultBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private ActivityAddMatchResultBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.cardview.widget.CardView) bindings[14]
            , (androidx.cardview.widget.CardView) bindings[15]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[2]
            , (com.google.android.material.textfield.TextInputEditText) bindings[13]
            , (com.google.android.material.textfield.TextInputEditText) bindings[12]
            , (com.google.android.material.textfield.TextInputEditText) bindings[8]
            , (com.google.android.material.textfield.TextInputEditText) bindings[7]
            , (com.google.android.material.textfield.TextInputEditText) bindings[19]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[20]
            , (android.widget.LinearLayout) bindings[4]
            , (com.facebook.drawee.view.SimpleDraweeView) bindings[16]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[11]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[22]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[6]
            , (education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding) bindings[1]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[18]
            , (android.widget.ImageView) bindings[17]
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