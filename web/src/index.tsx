import React from "react";
import ReactDOM from "react-dom/client";
import "./index.scss";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import "jquery/dist/jquery.min.js";
import "bootstrap/dist/js/bootstrap.min.js";
import {initializeApp} from "firebase/app";
import {getAuth} from "firebase/auth";

import Homepage from "./components/Homepage/Homepage";

import NotFound from "./components/NotFound/NotFound";
import {DefaultApi} from "./openapi";
import {API_ENDPOINT, firebaseConfig} from "./react-app-env";

import axios from "axios";
import LoginPage from "./components/LoginPage/LoginPage";
import OrganizationHub from "./components/OrganizationHub/OrganizationHub";

// Initialize Firebase
initializeApp(firebaseConfig);

const myaxios = axios.create();
myaxios.interceptors.request.use(
  async (config) => {
    if (config.headers === undefined) {
      return config;
    }
    // auth
    const token = (await getAuth().currentUser?.getIdToken(true)) ?? "" ?? "";
    config.headers.Authorization = "Bearer " + token;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const Api = new DefaultApi(undefined, API_ENDPOINT, myaxios);

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <NotFound />,
    children: [
      {
        path: "/",
        element: <Homepage />,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
    ],
  },
  {
    path: "/orgs",
    element: <OrganizationHub />,
  },
]);

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
