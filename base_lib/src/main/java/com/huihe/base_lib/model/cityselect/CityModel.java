package com.huihe.base_lib.model.cityselect;

import java.util.List;

public class CityModel {

    private LocationBean Location;

    public LocationBean getLocation() {
        return Location;
    }

    public void setLocation(LocationBean Location) {
        this.Location = Location;
    }

    public static class LocationBean {
        private List<CountryRegionBean> CountryRegion;

        public List<CountryRegionBean> getCountryRegion() {
            return CountryRegion;
        }

        public void setCountryRegion(List<CountryRegionBean> CountryRegion) {
            this.CountryRegion = CountryRegion;
        }

        public static class CountryRegionBean {

            private String Name;
            private String EName;
            private String Code;
            private List<StateBean> State;

            public String getName() {
                return Name;
            }
            public String getEName() {
                return EName;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public void setEName(String EName) {
                this.EName = EName;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public List<StateBean> getState() {
                return State;
            }

            public void setState(List<StateBean> State) {
                this.State = State;
            }

            public static class StateBean {

                private String Name;
                private String Code;
                private List<CityBean> City;

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getCode() {
                    return Code;
                }

                public void setCode(String Code) {
                    this.Code = Code;
                }

                public List<CityBean> getCity() {
                    return City;
                }

                public void setCity(List<CityBean> City) {
                    this.City = City;
                }

                public static class CityBean {


                    /**
                     * Name : 保定
                     * Code : 6
                     * Region : [{"Name":"新市区","Code":"2"},{"Name":"北市区","Code":"3"},{"Name":"南市区","Code":"4"},{"Name":"满城县","Code":"21"},{"Name":"清苑县","Code":"22"},{"Name":"涞水县","Code":"23"},{"Name":"阜平县","Code":"24"},{"Name":"徐水县","Code":"25"},{"Name":"定兴县","Code":"26"},{"Name":"唐　县","Code":"27"},{"Name":"高阳县","Code":"28"},{"Name":"容城县","Code":"29"},{"Name":"涞源县","Code":"30"},{"Name":"望都县","Code":"31"},{"Name":"安新县","Code":"32"},{"Name":"易　县","Code":"33"},{"Name":"曲阳县","Code":"34"},{"Name":"蠡　县","Code":"35"},{"Name":"顺平县","Code":"36"},{"Name":"博野县","Code":"37"},{"Name":"雄　县","Code":"38"},{"Name":"涿州市","Code":"81"},{"Name":"定州市","Code":"82"},{"Name":"安国市","Code":"83"},{"Name":"高碑店市","Code":"84"}]
                     */

                    private String Name;
                    private String Code;
                    private List<RegionBean> Region;

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public String getCode() {
                        return Code;
                    }

                    public void setCode(String Code) {
                        this.Code = Code;
                    }

                    public List<RegionBean> getRegion() {
                        return Region;
                    }

                    public void setRegion(List<RegionBean> Region) {
                        this.Region = Region;
                    }

                    public static class RegionBean {
                        /**
                         * Name : 新市区
                         * Code : 2
                         */

                        private String Name;
                        private String Code;

                        public String getName() {
                            return Name;
                        }

                        public void setName(String Name) {
                            this.Name = Name;
                        }

                        public String getCode() {
                            return Code;
                        }

                        public void setCode(String Code) {
                            this.Code = Code;
                        }
                    }
                }
            }
        }
    }
}
