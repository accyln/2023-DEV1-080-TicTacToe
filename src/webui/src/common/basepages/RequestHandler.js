import React from "react";
import {Navigate } from "react-router-dom";
import { PostSecure, GetSecure } from './RequestBase';


export function PostSecureBase(action, body, token) {
    return PostSecure(action, body, token).then(response => {
        if (response && response.ok) {
            if (response.status === 204) { //204 ise statüyü dön yoksa tüm response
                return response.status
            }
            else {
                return response.json()
            }
        }
        else if (response) {
            if(response.status === 401){
                alert("Unauthorized!!!");

            } 
            return Promise.reject(response);
            alert("An error was caught during the service call - " + response.statusText);

        }
    }
    ).then((responseJson) => {
        return responseJson;
      })
    .catch(
        (reason) => {
            reason.json().then((json) => {
                console.log(json);
                alert(json.message);
              })
           ;
        });
}

export function GetSecureBase(action, token) {
    return GetSecure(action, token).then(response => {
        if (response && response.ok) {
            if (response.status === 204) {
                return null;
            }
            else return response.json()
        }
        else if (response) {
            if (response.status === 401) {
                alert("Not authorized..");
            } else {
                alert("An error was caught during the service call. - " + response.statusText);
            }
        }
    }
    ).catch(
            (reason) => {
                debugger;
                alert(reason);
            });

}