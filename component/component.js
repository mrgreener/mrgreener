import React    from "react";
import template from "./component.jsx";

class component extends React.Component {
  render() {
    return template.call(this);
  }
}

export default component;
