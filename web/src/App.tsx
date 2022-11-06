import React, {useEffect, useState} from "react";
import logo from "./img/logo.svg";
import {Link, NavLink, Outlet} from "react-router-dom";

import "./App.css";
import {Image, Placeholder} from "react-bootstrap";
import {User} from "firebase/auth";
import {Api, auth} from "./index";
import {Profile} from "./openapi";

let activeClassName = "active";

function App(this: any) {
  useEffect(() => {
    document.title = "MrGreener";
  }, []);

  const [loaded, updateLoaded] = useState<boolean>(false);
  const [user, updateUser] = useState<User | undefined>();
  const [profile, updateProfile] = useState<Profile | undefined>();

  setInterval(() => {
    Api.getMeGet().then(res => {
      updateProfile(res.data);
    });
  }, 2500)

  // let profileLoaded = false;

  auth
    .onAuthStateChanged((user_) => {
      updateUser(() => {
        updateLoaded(true);
        if (user_ == null) return undefined;
        else {
          // if (!profileLoaded) {
          //
          //   profileLoaded = true;
          // }
          return user_;
        }
      });
    })
    .bind(this);

  return (
    <>
      <nav className="navbar navbar-expand-md mb-5 bg-secondary">
        <div className="container-fluid col-md-8 px-3 mx-auto">
          <Link to={"/"} className="navbar-brand">
            <Image
              src={logo}
              className={"d-inline-block align-top"}
              height={"30px"}
              width={"30px"}
            ></Image>
            <span style={{ paddingLeft: "0.3rem" }}>MrGreener</span>
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNavDropdown">
            <ul className="navbar-nav" style={{ marginRight: "auto" }}>
              <li className="nav-item">
                <NavLink
                  to="/"
                  className={({ isActive }) =>
                    "nav-link " + (isActive ? activeClassName : "")
                  }
                >
                  Home
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink
                  to="/earn"
                  className={({ isActive }) =>
                    "nav-link " + (isActive ? activeClassName : "")
                  }
                >
                  Earn
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink
                  to="/use"
                  className={({ isActive }) =>
                    "nav-link " + (isActive ? activeClassName : "")
                  }
                >
                  Use
                </NavLink>
              </li>
              <li>
                <Placeholder
                    as={"span"}
                    className="navbar-text actions"
                    animation="glow"
                >
                  {loaded ? (
                      <>
                        {user === undefined && (<></>)}
                        {user !== undefined &&
                            <NavLink
                                to='/profile/${user.uid ?? "null"}'
                                className={({ isActive }) =>
                                    "nav-link " + (isActive ? activeClassName : "")
                                }
                            >
                              Profile
                            </NavLink>
                        }
                      </>
                  ) : (
                      <Placeholder xs={4} />
                  )}
                </Placeholder>
              </li>
              <li>
                <Placeholder
                    as={"span"}
                    className="navbar-text actions"
                    animation="glow"
                >
                  {loaded ? (
                      <>
                        {user === undefined && (<></>)}
                        {user !== undefined &&
                            <NavLink
                                to='/rewards/${user.uid ?? "null"}'
                                className={({ isActive }) =>
                                    "nav-link " + (isActive ? activeClassName : "")
                                }
                            >
                              Rewards
                            </NavLink>
                        }
                      </>
                  ) : (
                      <Placeholder xs={4} />
                  )}
                </Placeholder>
              </li>
            </ul>
            <Placeholder
              as={"span"}
              className="navbar-text actions"
              animation="glow"
            >
              {loaded ? (
                <>
                  {user === undefined && (
                    <Link
                      to={"/login"}
                      role="button"
                      className="btn action-button btn-primary my-2 my-sm-0"
                      id="login_button"
                    >
                      Login
                    </Link>
                  )}
                  {
                    user !== undefined && <p>Hi {user.displayName ?? "null"}, you have &nbsp;
                    <b style={{ color: "#119621" }}>{profile?.points} points.</b></p>
                  }
                </>
              ) : (
                <Placeholder xs={4} />
              )}
            </Placeholder>
          </div>
        </div>
      </nav>
      <div className="col-md-8 px-3 mx-auto">
        <Outlet />
        <hr className="my-5" />
        <footer>
          <div style={{ textAlign: "center" }}>
            <p className="text-muted">
              Created by the MrGreener Team. Licensed Apache 2.
            </p>
            <p>
              <a
                data-bs-toggle="collapse"
                href="#collapseImprint"
                role="button"
                aria-expanded="false"
                aria-controls="collapseImprint"
              >
                Imprint
              </a>
            </p>
            <div className="collapse" id="collapseImprint">
              <div className="card card-body">
                <p className="text-muted">
                  This website is student hackathon project and does not
                  necessarily reflect Jacobs University Bremen opinions. Jacobs
                  University Bremen does not endorse this site, nor is it
                  checked by Jacobs University Bremen regularly, nor is it part
                  of the official Jacobs University Bremen web presence. For
                  each external link existing on this website, we initially have
                  checked that the target page does not contain contents which
                  is illegal wrt. German jurisdiction. However, as we have no
                  influence on such contents, this may change without our
                  notice. Therefore we deny any responsibility for the websites
                  referenced through our external links from here. No
                  information conflicting with GDPR is stored in the server.
                </p>
              </div>
            </div>

            <p className="text-muted">
              &#123;a dot kovrigin, k dot ivanov, m dot ipatov, pe dot
              tsvetkov&#125; at jacobs-university dot de
            </p>
          </div>
        </footer>
      </div>
    </>
  );
}

export default App;
