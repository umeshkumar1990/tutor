package com.app.studio.tutor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.studio.tutor.R;
import com.app.studio.tutor.model.TutorBean;

import java.util.ArrayList;


public class TutorAdapter extends BaseAdapter {

	Context 			 context;
	LayoutInflater		 inflater;
	ArrayList<TutorBean> tutorArrayList;

	public TutorAdapter(Context context, ArrayList<TutorBean> tutorArrayList) {

		this.context	    =	context;
		this.tutorArrayList	=	tutorArrayList;
		inflater		    =	LayoutInflater.from(context);
	}

	@Override
	public int getCount() {	return tutorArrayList.size(); }

	@Override
	public Object getItem(int position) {
		return tutorArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder holder;

		if (view == null) {
			view						=	inflater.inflate(R.layout.tutor_list_item, null);
			holder 						= 	new ViewHolder();
			holder.txtViewTutor 		=	(TextView)view.findViewById(R.id.textView_tutor_text);
			holder.txtViewDistance		=	(TextView)view.findViewById(R.id.textView_distance);
			holder.txtViewLocation 		=	(TextView)view.findViewById(R.id.textView_location);
			holder.txtViewSkills		=	(TextView)view.findViewById(R.id.textView_skills);
			//holder.imageViewTutor		=	(ImageView)view.findViewById(R.id.imageView_tutor);
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}

		TutorBean data	=	tutorArrayList.get(position);

		holder.txtViewDistance.setText(data.getDistance());
		holder.txtViewTutor.setText(data.getTutor());
		holder.txtViewLocation.setText(data.getLocation());
		holder.txtViewSkills.setText(data.getSkills());
		return view;
	}

	static class ViewHolder
	{
		private TextView	txtViewDistance;
		private TextView	txtViewLocation;
		private TextView	txtViewSkills;
		private TextView	txtViewTutor;
		//private ImageView   imageViewTutor;
	}
}
