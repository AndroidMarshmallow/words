package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yukon.absenceplanner.mobile.android.R;
import com.yukon.absenceplanner.mobile.android.Values;
import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date.DateFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AbsenceInfoPersonAdapter extends
        RecyclerView.Adapter<AbsenceInfoPersonAdapter.AbsenceViewHolder> {

    private List<Absence> listAbsence = new ArrayList<>();

    private final OnItemClickListener onItemClickListener;

    private final OnLinerLayoutClickListener onLinerLayoutClickListener;

    private Context context;

    public AbsenceInfoPersonAdapter(List<Absence> list, OnItemClickListener listener, OnLinerLayoutClickListener onLinerLayoutClickListener, Context context) {
        this.onItemClickListener = listener;
        this.onLinerLayoutClickListener=onLinerLayoutClickListener;
        this.context = context;
    }

    public void refreshAbsenceInfo(List<Absence> list){
        listAbsence.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public AbsenceInfoPersonAdapter.AbsenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.absence_info_item, parent, false);
        AbsenceViewHolder pvh = new AbsenceViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AbsenceInfoPersonAdapter.AbsenceViewHolder holder, int position) {
        holder.bind(listAbsence.get(position), onItemClickListener);
        holder.bindLayout(onLinerLayoutClickListener);
        try {
            fullDataAbsenceInfo(holder, listAbsence.get(position));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listAbsence.size();
    }

    public void fullDataAbsenceInfo(AbsenceViewHolder viewHolder, Absence absence) throws ParseException {
        viewHolder.typeName.setText(absence.getAbsenceTypeName());
        viewHolder.dataAndTime.setText(String.valueOf(DateFactory.getDateFormat(absence.getStartDateTime())+" - "+ DateFactory.getDateFormat(absence.getEndDateTime())));
        visibleCancelButton(viewHolder,absence);
        colorForStatus(viewHolder,absence);
        logbooksWriter(viewHolder,absence);
        viewHolder.linerLayoutForUpdateInfo.setVisibility(View.GONE);
        viewHolder.linerLayoutForInfo.setVisibility(View.GONE);
    }

    public void visibleCancelButton(AbsenceViewHolder holder,Absence absence){
        switch (absence.getCanCancel()) {
            case Values.TRUE:
                holder.btDeleteAbsence.setVisibility(View.VISIBLE);
                break;
            case Values.FALSE:
                holder.btDeleteAbsence.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void logbooksWriter(AbsenceViewHolder holder,Absence absence) throws ParseException {
        if(absence.getLogbooks().size()<2) {
            holder.timeWhenCreated.setText(DateFactory.getDateFormat(absence.getLogbooks().get(0).getTimestamp()));
            holder.whoCreated.setText(absence.getLogbooks().get(0).getByEmployee());
            holder.tvStatusCreated.setText(absence.getLogbooks().get(0).getStatus());
            if (absence.getLogbooks().get(0).getNote().equals("")) {
                holder.tvNote.setVisibility(View.GONE);
            } else {
                holder.tvNote.setVisibility(View.VISIBLE);
                holder.tvNote.setText(absence.getLogbooks().get(0).getNote());
            }
            holder.timeWhenUpdate.setText("");
            holder.whoUpdate.setText("");
            holder.tvUpdateStatus.setText("");
        } else {

            holder.timeWhenUpdate.setText(DateFactory.getDateFormat(absence.getLogbooks().get(0).getTimestamp()));
            holder.whoUpdate.setText(absence.getLogbooks().get(0).getByEmployee());
            holder.tvUpdateStatus.setText(absence.getLogbooks().get(0).getStatus());
            if(absence.getLogbooks().get(0).getNote().equals("")){
                holder.tvNoteUpdate.setVisibility(View.GONE);
            } else {
                holder.tvNoteUpdate.setVisibility(View.VISIBLE);
                holder.tvNoteUpdate.setText(absence.getLogbooks().get(0).getNote());
            }

            holder.timeWhenCreated.setText(DateFactory.getDateFormat(absence.getLogbooks().get(1).getTimestamp()));
            holder.whoCreated.setText(absence.getLogbooks().get(1).getByEmployee());
            holder.tvStatusCreated.setText(absence.getLogbooks().get(1).getStatus());
            if (absence.getLogbooks().get(1).getNote().equals("")) {
                holder.tvNote.setVisibility(View.GONE);
            } else {
                holder.tvNote.setVisibility(View.VISIBLE);
                holder.tvNote.setText(absence.getLogbooks().get(1).getNote());
            }
        }
    }

    public void colorForStatus(AbsenceViewHolder holder, Absence absence){
        switch (absence.getStatus()){
            case Values.APPROVED:
                holder.llStatusColorOne.setBackgroundResource(R.drawable.background_approved);
                holder.llStatusColorTwo.setBackgroundResource(R.drawable.background_approved);
            break;

            case Values.WAITING:
                holder.llStatusColorOne.setBackgroundColor(Color.rgb(255,255,255));
                holder.llStatusColorTwo.setBackgroundColor(Color.rgb(255,255,255));
            break;

            case Values.REJECTED:
                holder.llStatusColorOne.setBackgroundColor(ContextCompat.getColor(context, R.color.rejected));
                holder.llStatusColorTwo.setBackgroundColor(ContextCompat.getColor(context, R.color.rejected));
            break;
        }
    }

    public class AbsenceViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.typeName)
        protected TextView typeName;

        @Nullable
        @BindView(R.id.dataAndTime)
        protected TextView dataAndTime;

        @Nullable
        @BindView(R.id.btDeleteAbsence)
        protected Button btDeleteAbsence;

        @Nullable
        @BindView(R.id.linerLayoutClickListener)
        protected LinearLayout linerLayoutClickListener;

        @Nullable
        @BindView(R.id.linerLayoutForInfo)
        protected LinearLayout linerLayoutForInfo;

        @Nullable
        @BindView(R.id.linerLayoutForUpdateInfo)
        protected LinearLayout linerLayoutForUpdateInfo;

        @Nullable
        @BindView(R.id.llStatusColorOne)
        protected LinearLayout llStatusColorOne;

        @Nullable
        @BindView(R.id.llStatusColorTwo)
        protected LinearLayout llStatusColorTwo;

        @Nullable
        @BindView(R.id.timeWhenUpdate)
        protected TextView timeWhenUpdate;

        @Nullable
        @BindView(R.id.tvUpdateStatus)
        protected TextView tvUpdateStatus;

        @Nullable
        @BindView(R.id.whoUpdate)
        protected TextView whoUpdate;

        @Nullable
        @BindView(R.id.tvNoteUpdate)
        protected TextView tvNoteUpdate;

        @Nullable
        @BindView(R.id.timeWhenCreated)
        protected TextView timeWhenCreated;

        @Nullable
        @BindView(R.id.tvStatusCreated)
        protected TextView tvStatusCreated;

        @Nullable
        @BindView(R.id.whoCreated)
        protected TextView whoCreated;

        @Nullable
        @BindView(R.id.tvNote)
        protected TextView tvNote;

        @Nullable
        @BindView(R.id.llLineBetweenLayout)
        protected LinearLayout llLineBetweenLayout;

        public AbsenceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Absence item, final OnItemClickListener listener) {
            btDeleteAbsence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

        public void bindLayout(final OnLinerLayoutClickListener listener) {
            linerLayoutClickListener.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(linerLayoutForInfo.getVisibility()==View.GONE) {
                        linerLayoutForInfo.setVisibility(v.VISIBLE);
                        if((timeWhenUpdate.getText().equals(""))||(tvUpdateStatus.getText().equals(""))||(whoUpdate.getText().equals(""))){
                            linerLayoutForUpdateInfo.setVisibility(v.GONE);
                            llLineBetweenLayout.setVisibility(View.GONE);
                        } else {
                            linerLayoutForUpdateInfo.setVisibility(v.VISIBLE);
                        }
                    } else {
                        linerLayoutForInfo.setVisibility(v.GONE);
                        linerLayoutForUpdateInfo.setVisibility(v.GONE);
                    }
                }
            });
        }
    }
}
