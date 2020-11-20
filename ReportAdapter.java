package com.example.transport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.Holder> {

    Context context;
    ArrayList<Transport_Model> transport_models;

    public ReportAdapter(Context context, ArrayList<Transport_Model> transport_models) {
        this.context = context;
        this.transport_models = transport_models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(transport_models.get(position).getNAME());
        holder.phoneno.setText(transport_models.get(position).getPHONENO());
        holder.type_of_tank.setText(transport_models.get(position).getTYPE_OF_TANK());
        holder.ro_tank.setText(transport_models.get(position).getRO_NAME());
        holder.district_office_name.setText(transport_models.get(position).getDISTRICT_OFFICE_NAME());
        holder.project_office_name.setText(transport_models.get(position).getPROJECT_OFFICE_NAME());
        holder.village_name.setText(transport_models.get(position).getVILLAGE_NAME());
        holder.tank_name.setText(transport_models.get(position).getTANK_NAME());
        holder.work_start_time.setText(transport_models.get(position).getWORK_START_TIME());
        holder.number_of_machine_deployed.setText(transport_models.get(position).getNO_MACHINE_DEPLOYED());
        holder.jcb_work_end_time.setText(transport_models.get(position).getJCB_WORK_END_TIME());
        holder.h_start_time.setText(transport_models.get(position).getHITACHI_WORK_START_TIME());
        holder.h_end_time.setText(transport_models.get(position).getHITACHI_WORK_END_TIME());
        holder.tank_name.setText(transport_models.get(position).getTANK_NAME());
        holder.slit_transportation.setText(transport_models.get(position).getSILT_TRANSPORTATION());
        holder.no_slit_transportation.setText(transport_models.get(position).getNO_SILT_TRANSPORTATION());
        holder.role.setText(transport_models.get(position).getRole());
    }

    @Override
    public int getItemCount() {
        return transport_models.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView name, phoneno, type_of_tank, ro_tank, district_office_name, project_office_name,
                village_name, tank_name, work_start_time, number_of_machine_deployed, jcb_work_end_time,
                h_start_time, h_end_time, slit_transportation, no_slit_transportation, role;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneno = itemView.findViewById(R.id.phoneno);
            type_of_tank = itemView.findViewById(R.id.type_of_tank);
            ro_tank = itemView.findViewById(R.id.ro_tank);
            district_office_name = itemView.findViewById(R.id.district_office_name);
            project_office_name = itemView.findViewById(R.id.project_office_name);
            village_name = itemView.findViewById(R.id.village_name);
            tank_name = itemView.findViewById(R.id.tank_name);
            work_start_time = itemView.findViewById(R.id.work_start_time);
            number_of_machine_deployed = itemView.findViewById(R.id.number_of_machine_deployed);
            jcb_work_end_time = itemView.findViewById(R.id.jcb_work_end_time);
            h_start_time = itemView.findViewById(R.id.h_start_time);
            h_end_time = itemView.findViewById(R.id.h_end_time);
            slit_transportation = itemView.findViewById(R.id.slit_transportation);
            no_slit_transportation = itemView.findViewById(R.id.no_slit_transportation);
            role = itemView.findViewById(R.id.role);

        }
    }
}
