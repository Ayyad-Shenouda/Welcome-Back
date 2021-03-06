package com.project.graduation.welcomeback.Home.MissingStepper;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.graduation.welcomeback.Home.Data.DataManger;
import com.project.graduation.welcomeback.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Ahmed on 5/6/2017.
 */

public class ReportMissingStep2 extends Fragment implements BlockingStep {

    private DataManger mDataManager;

    private String mLocation,mContact,mMoreInfo;

    private ArrayList<String> mReportArrayList;

    private EditText mLocationEditText,mMoreInfoEditText,mContactInfoEditText;

    public static ReportMissingStep2 newInstance(){
        return new ReportMissingStep2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_missing_step2_fragment,container,false);
        mDataManager= (DataManger) getActivity();
        mLocationEditText = (EditText) view.findViewById(R.id.missing_step2_location_editText);
        mContactInfoEditText = (EditText) view.findViewById(R.id.missing_step2_contact_info_editText);
        mMoreInfoEditText = (EditText) view.findViewById(R.id.missing_step2_moreInfo_editText);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataManger) {
            mDataManager = (DataManger) context;
        } else {
            throw new IllegalStateException("Activity must implement DataManager interface!");
        }
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        mReportArrayList = mDataManager.getData();
        mReportArrayList.add(3,mLocation);
        mReportArrayList.add(4,mContact);
        mReportArrayList.add(5,mMoreInfo);

        mDataManager.saveData(mReportArrayList);
        callback.goToNextStep();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();

    }

    @Override
    public VerificationError verifyStep() {
        mLocation = mLocationEditText.getText().toString();
        mContact = mContactInfoEditText.getText().toString();
        mMoreInfo = mMoreInfoEditText.getText().toString();

        if(mLocation == null || mLocation.equals("")){
            return new VerificationError("please enter where he/she was last seen.");
        }else if(mContact == null || mContact.equals("")){
            return  new VerificationError("please enter ur email or phone.");
        }

        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(getActivity(),error.getErrorMessage(),Toast.LENGTH_LONG).show();
    }

}
