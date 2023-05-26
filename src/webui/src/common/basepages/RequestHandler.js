import React from "react";
import {Navigate } from "react-router-dom";
import { PostSecure, GetSecure } from './RequestBase';


export function PostSecureBase(action, body) {
    return PostSecure(action, body).then(response => {
        if (response && response.ok) {
            return response.json()
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

export function GetSecureBase(action) {
    return GetSecure(action).then(response => {
        if (response && response.ok) {
            return response.json()
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