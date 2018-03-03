package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.getupsolucoesdigitais.githubrequestmatcher.R;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.model.Repository;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RepositoryViewHolder> {

    private List<Repository> repositoryList;
    private OnItemClickListener onItemClickListener;

    public MainAdapter(List<Repository> repositoryList, OnItemClickListener onItemClickListener) {
        this.repositoryList = repositoryList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(
                parent.getContext())
                .inflate(R.layout.item_repository, parent, false);

        return new RepositoryViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.bindRepository(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return (repositoryList != null ? repositoryList.size() : 0);
    }

    static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        private TextView textRepositoryName;
        private TextView textRepositoryDescription;
        private TextView textOwnerLogin;
        private ImageView imageRepositoryAvatar;
        private Repository repository;

        public RepositoryViewHolder(final View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            textRepositoryName = itemView.findViewById(R.id.text_repository_name);
            textRepositoryDescription = itemView.findViewById(R.id.text_repository_description);
            textOwnerLogin = itemView.findViewById(R.id.text_owner_login);
            imageRepositoryAvatar = itemView.findViewById(R.id.image_owner_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(repository);
                    }
                }
            });
        }

        public void bindRepository(Repository repository) {
            this.repository = repository;

            textRepositoryName.setText(repository.getName());
            textRepositoryDescription.setText(repository.getDescription());
            textOwnerLogin.setText(repository.getOwner().getLogin());
            Picasso.with(imageRepositoryAvatar.getContext())
                    .load(repository.getOwner().getAvatarUrl())
                    .into(imageRepositoryAvatar);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Repository repository);
    }
}
