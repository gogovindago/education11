package education.hry.pkl.cricket11.databinding;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProfileBindingImpl extends ActivityProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.constraintLayout, 1);
        sViewsWithIds.put(R.id.edit, 2);
        sViewsWithIds.put(R.id.save, 3);
        sViewsWithIds.put(R.id.txtAccountCreatedat, 4);
        sViewsWithIds.put(R.id.materialCardView, 5);
        sViewsWithIds.put(R.id.profile_image, 6);
        sViewsWithIds.put(R.id.takePhoto, 7);
        sViewsWithIds.put(R.id.edtRegistraionId, 8);
        sViewsWithIds.put(R.id.edtfirstname, 9);
        sViewsWithIds.put(R.id.edtlastname, 10);
        sViewsWithIds.put(R.id.edtmobile, 11);
        sViewsWithIds.put(R.id.edtemail, 12);
        sViewsWithIds.put(R.id.edtgender, 13);
        sViewsWithIds.put(R.id.allll, 14);
        sViewsWithIds.put(R.id.edtdistrict, 15);
        sViewsWithIds.put(R.id.otherstatell, 16);
        sViewsWithIds.put(R.id.otherState, 17);
        sViewsWithIds.put(R.id.otherdistricll, 18);
        sViewsWithIds.put(R.id.otherdistrict, 19);
        sViewsWithIds.put(R.id.otherprofessionll, 20);
        sViewsWithIds.put(R.id.otherprofession, 21);
        sViewsWithIds.put(R.id.allcollege, 22);
        sViewsWithIds.put(R.id.edtcollegeName, 23);
        sViewsWithIds.put(R.id.alldesignation, 24);
        sViewsWithIds.put(R.id.edtfacultyDesignation, 25);
        sViewsWithIds.put(R.id.llstudentdata, 26);
        sViewsWithIds.put(R.id.edtcoursetype, 27);
        sViewsWithIds.put(R.id.edtcourseName, 28);
        sViewsWithIds.put(R.id.edtyearName, 29);
        sViewsWithIds.put(R.id.btn_update, 30);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds));
    }
    private ActivityProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.textfield.TextInputLayout) bindings[22]
            , (com.google.android.material.textfield.TextInputLayout) bindings[24]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (androidx.appcompat.widget.AppCompatButton) bindings[30]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.ImageView) bindings[2]
            , (com.google.android.material.textfield.TextInputEditText) bindings[8]
            , (com.google.android.material.textfield.TextInputEditText) bindings[23]
            , (com.google.android.material.textfield.TextInputEditText) bindings[28]
            , (com.google.android.material.textfield.TextInputEditText) bindings[27]
            , (com.google.android.material.textfield.TextInputEditText) bindings[15]
            , (com.google.android.material.textfield.TextInputEditText) bindings[12]
            , (com.google.android.material.textfield.TextInputEditText) bindings[25]
            , (com.google.android.material.textfield.TextInputEditText) bindings[9]
            , (com.google.android.material.textfield.TextInputEditText) bindings[13]
            , (com.google.android.material.textfield.TextInputEditText) bindings[10]
            , (com.google.android.material.textfield.TextInputEditText) bindings[11]
            , (com.google.android.material.textfield.TextInputEditText) bindings[29]
            , (android.widget.LinearLayout) bindings[26]
            , (androidx.cardview.widget.CardView) bindings[5]
            , (com.google.android.material.textfield.TextInputEditText) bindings[17]
            , (com.google.android.material.textfield.TextInputLayout) bindings[18]
            , (com.google.android.material.textfield.TextInputEditText) bindings[19]
            , (com.google.android.material.textfield.TextInputEditText) bindings[21]
            , (com.google.android.material.textfield.TextInputLayout) bindings[20]
            , (com.google.android.material.textfield.TextInputLayout) bindings[16]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[3]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.TextView) bindings[4]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
        this.mboundView0.setTag(null);
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