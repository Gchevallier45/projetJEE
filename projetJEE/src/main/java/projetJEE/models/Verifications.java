/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.models;

import java.time.LocalTime;
import java.util.regex.Pattern;

/**
 *
 * @author remit
 */
public class Verifications {

        // regex rules
        private Pattern nameStoreRegex =Pattern.compile("[a-zA-ZÀ-ÿ][a-zA-ZÀ-ÿ_0-9 ]{2,49}");
        private Pattern emailRegex = Pattern.compile("[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([_\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})");
        private Pattern phoneNumberRegex = Pattern.compile("(01|02|03|04|05|06|07|08|09)[ \\.\\-]?[0-9]{2}[ \\.\\-]?[0-9]{2}[ \\.\\-]?[0-9]{2}[ \\.\\-]?[0-9]{2}");
        private Pattern streetRegex = Pattern.compile("[a-zA-ZÀ-ÿ0-9][a-zA-ZÀ-ÿ_0-9 ]{2,49}"); 
        private Pattern cityRegex = Pattern.compile("[a-zA-ZÀ-ÿ][a-zA-ZÀ-ÿ_0-9 ]{2,49}");
        private Pattern zipCodeRegex = Pattern.compile("([A-Z]+[A-Z]?\\-)?[0-9]{1,2} ?[0-9]{3}");
        private Pattern stateRegex = Pattern.compile("[a-zA-ZÀ-ÿ][a-zA-ZÀ-ÿ_0-9 ]{2,49}");
        private Pattern countryRegex = Pattern.compile("[a-zA-ZÀ-ÿ][a-zA-ZÀ-ÿ_0-9 ]{2,49}");

        /**
         * Default constructor
         */
        public Verifications() {
        }
        
        /**
         * Verification of the name format
         * @param name
         * @return true if the parameter is correct
         * @throws Exception 
         */
        public boolean storeNameVerification(String name) throws Exception {
            if(!nameStoreRegex.matcher(name).matches()) {
                    throw new Exception("The store name must be from 3 to 50 characters.");
            }
            return true;
        }
        
        /**
         * Email check
         * @param email
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean emailVerification(String email) throws Exception {
            if(!emailRegex.matcher(email).matches()) {
                    throw new Exception("The email address is invalid.");
            }
            return true;
        }
        
        /**
         * phone number check
         * @param phoneNumber
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean phoneNumberVerification(String phoneNumber) throws Exception {
            if(!phoneNumberRegex.matcher(phoneNumber).matches()) {
                    throw new Exception("The phone number is not valid. It must have 10 digits.");
            }
            return true;
        }
        
        /**
         * street check
         * @param street
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean streetVerification(String street) throws Exception {
            if(!streetRegex.matcher(street).matches()) {
                    throw new Exception("The street must be from 3 to 50 characters.");
            }
            return true;
        }
        
        /**
         * city check
         * @param city
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean cityVerification(String city) throws Exception {
            if(!cityRegex.matcher(city).matches()) {
                    throw new Exception("The city must be from 3 to 50 characters.");
            }
            return true;
        }
        
        /**
         * zipe code check
         * @param zipeCode
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean zipCodeVerification(String zipeCode) throws Exception {
            if(!zipCodeRegex.matcher(zipeCode).matches()) {
                    throw new Exception("The zipe code must contain 5 digits, for example 37000 for the city of Tours.");
            }
            return true;
        }
        
        /**
         * state check
         * @param state
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean stateVerification(String state) throws Exception {
            if(!stateRegex.matcher(state).matches()) {
                    throw new Exception("The state must be from 3 to 50 characters.");
            }
            return true;
        }
        
        /**
         * country check
         * @param country
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean countryVerification(String country) throws Exception {
            if(!countryRegex.matcher(country).matches()) {
                    throw new Exception("The country must be from 3 to 50 characters.");
            }
            return true;
        }
        
        /**
         * openingHour check
         * @param country
         * @return true if the parameter is correct
         * @throws Exception if the parameter is incorrect
         */
        public boolean openingHourVerification(String day, String openHour, String closeHour, boolean isClosed, boolean is24hrs) throws Exception {
            LocalTime openHourLT = null;
            LocalTime closeHourLT = null;
            
            // format test
            if(isClosed == false && is24hrs == false) {
                try{
                     openHourLT = LocalTime.parse(openHour+":00");
                }
                catch(Exception e) {
                    throw new Exception(day+"'s start time is not in the correct format. Example: 9:30 for 9:30");
                }

                try{
                     closeHourLT = LocalTime.parse(openHour+":00");
                }
                catch(Exception e) {
                    throw new Exception(day+"'s close time is not in the correct format. Example: 20:30 for 20:30");
                }
            }
            
            if((isClosed == false && is24hrs == false) && openHourLT.isAfter(closeHourLT))
                throw new Exception("The open hour is after of close hour.");
            
            if(isClosed == true && is24hrs == true) 
                throw new Exception("The store don't is close and open 24 hours on" + day);
            
            return true;
        }
        
        
}
