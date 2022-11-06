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

import NotFound from "./components/NotFound/NotFound";
import {CustomerAPIApi} from "./openapi";
import {API_ENDPOINT, firebaseConfig} from "./react-app-env";

import axios from "axios";
import LoginPage from "./components/LoginPage/LoginPage";
import LandingPage from "./components/LandingPage/LandingPage";
import EarnPage from "./components/EarnPage/EarnPage";
import SpendPage from "./components/SpendPage/SpendPage";
import RedeemPage from "./components/RedeemPage/RedeemPage";
import RewardDetail from "./components/RewardDetail/RewardDetail";
import PromotionDetail from "./components/PromotionDetail/PromotionDetail";
import ProfilePage from "./components/Profile/Profile";
import OrganisationPromotionsPage from "./components/OrganizationPromotions/OrganisationPromotions";
import OrganisationRewardsPage from "./components/OrganizationRewards/OrganizationRewards";
import UsePage from "./components/UsePage/UsePage";

// Initialize Firebase
initializeApp(firebaseConfig);

export const auth = getAuth();

const myaxios = axios.create();
myaxios.interceptors.request.use(
  async (config) => {
    if (config.headers === undefined) {
      return config;
    }
    // auth
    const token1 = (await auth.currentUser?.getIdToken(false));
    let token = "";
    if (token1 === undefined) {
      console.warn("No token! Attempting again");
      await new Promise( resolve => setTimeout(resolve, 1500) );
      const token2 = (await auth.currentUser?.getIdToken(true));
      if (token2 === undefined) {
        console.error("Twice no token!");
      } else {
        token = token2;
      }
    } else {
      token = token1
    }
    config.headers.Authorization = "Bearer " + token;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const Api = new CustomerAPIApi(undefined, API_ENDPOINT, myaxios);

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <NotFound />,
    children: [
      {
        path: "/",
        element: <LandingPage />,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
      {
        path: "/earn",
        element: <EarnPage/>
      },
      {
        path: "/use",
        element: <UsePage/>
      },
      {
        path: "/spend",
        element: <SpendPage/>
      },
      {
        path: "/redeem",
        element: <RedeemPage/>
      },
      {
        path: "/reward/:id",
        element: <RewardDetail/>
      },
      {
        path: "/promotion/:id",
        element: <PromotionDetail/>
      },
      {
        path: "/profile/:id",
        element: <ProfilePage/>
      },
      {
        path: "/organization/:id/promotions",
        element: <OrganisationPromotionsPage/>
      },
      {
        path: "/organization/:id/rewards",
        element: <OrganisationRewardsPage/>
      }
    ],
  }
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
