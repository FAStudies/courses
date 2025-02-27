// Create a react app from scratch
// It should display 2 paragraph HTML elements
// The paragraph should say:
// Created by YOURNAME
// Copyright CURRENTYEAR
// E.g.
// Created by Angela Yu.
// Copyright 2019.

import React from "react";
import ReactDOM from "react-dom";

const yourname = "Harsh Chaturvedi";
const currentyear = new Date().getFullYear();

ReactDOM.render(
  <div>
    <p>Created by {yourname}</p>
    <p>Copyright {currentyear}</p>
  </div>,
  document.getElementById("root")
);
