package models;

public class OAuthModel {

	private String birthday;// date
	private String etag;
	private Image image;
	private String isPlusUser;// boolean
	private Emails[] emails;
	private String kind;
	private String url;
	private AgeRange ageRange;
	private String id;// int
	private String verified;// boolean
	private Name name;
	private String domain;
	private String circledByCount;// int
	private String gender;
	private String language;
	private String displayName;
	private String objectType;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getIsPlusUser() {
		return isPlusUser;
	}

	public void setIsPlusUser(String isPlusUser) {
		this.isPlusUser = isPlusUser;
	}

	public Emails[] getEmails() {
		return emails;
	}

	public void setEmails(Emails[] emails) {
		this.emails = emails;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public AgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCircledByCount() {
		return circledByCount;
	}

	public void setCircledByCount(String circledByCount) {
		this.circledByCount = circledByCount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Override
	public String toString() {
		return "ClassPojo [birthday = " + birthday + ", etag = " + etag + ", image = " + image + ", isPlusUser = "
				+ isPlusUser + ", emails = " + emails + ", kind = " + kind + ", url = " + url + ", ageRange = "
				+ ageRange + ", id = " + id + ", verified = " + verified + ", name = " + name + ", domain = " + domain
				+ ", circledByCount = " + circledByCount + ", gender = " + gender + ", language = " + language
				+ ", displayName = " + displayName + ", objectType = " + objectType + "]";
	}

	class AgeRange {
		private String min;

		public String getMin() {
			return min;
		}

		public void setMin(String min) {
			this.min = min;
		}

		@Override
		public String toString() {
			return "ClassPojo [min = " + min + "]";
		}
	}

	public class Emails {
		private String value;

		private String type;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return "ClassPojo [value = " + value + ", type = " + type + "]";
		}
	}

	class Image {
		private String isDefault;

		private String url;

		public String getIsDefault() {
			return isDefault;
		}

		public void setIsDefault(String isDefault) {
			this.isDefault = isDefault;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public String toString() {
			return "ClassPojo [isDefault = " + isDefault + ", url = " + url + "]";
		}
	}

	public class Name {
		private String familyName;

		private String givenName;

		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public String getGivenName() {
			return givenName;
		}

		public void setGivenName(String givenName) {
			this.givenName = givenName;
		}

		@Override
		public String toString() {
			return "ClassPojo [familyName = " + familyName + ", givenName = " + givenName + "]";
		}
	}
}