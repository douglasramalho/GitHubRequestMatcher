package br.com.getupsolucoesdigitais.githubrequestmatcher.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable {
    private final String name;
    private final String description;
    private final Owner owner;

    public Repository(String name, String description, Owner owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.owner, flags);
    }

    protected Repository(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
