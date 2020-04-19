import React, { useState } from 'react'
import { register } from '../../../utils/session'
import { connect } from 'react-redux'

export const AddOffer = props => {
  function readURL(input) {
    console.log(input.target.files)
    if (input.target.files && input.target.files[0]) {
      var reader = new FileReader()

      reader.onload = function(e) {
        document.getElementById('blah').src = e.target.result
      }

      reader.readAsDataURL(input.target.files[0])
    }
  }

  const handleSubmit = async event => {
    event.preventDefault()
    var data = new FormData(event.target)
    var request = new XMLHttpRequest();
  request.open("POST", "/offers/add");
  request.send(data);
  if(request.status == 200)
    {
    alert("dodano oferte")
    }
  else
    {
    alert("nie udalo sie dodać oferty")
    }
  }


  return (
    <div>
      <div className="content-box flexcolumn search searchmain">
        <div className="flexrow">
          <form
            action="offer/add"
            className="flexrow fullwidth"
            method="post"
            enctype="multipart-form/data"
          >
            <div className="col25 center">
              <img
                className="addpic"
                id="blah"
                src="http://placehold.it/180"
                alt="your image"
              />
              <div>
                <input type="file" id="imgInp" onChange={readURL} />
              </div>
            </div>
            <div className="col75">
              <div className="title">
                <input
                  className="addtitle"
                  type="text"
                  placeholder="title"
                ></input>
              </div>
              <div>
                <input
                  className="marginer width33"
                  type="text"
                  placeholder="price"
                ></input>
                <input
                  className="marginer width33"
                  type="text"
                  placeholder="date"
                ></input>
                <input
                  className="marginer width33"
                  type="text"
                  placeholder="localization"
                ></input>
              </div>
              <div className="marginer">
                <textarea className="adddesc" placeholder="description" />
              </div>
              <div className="center">
                <button className="btn-blue" onClick={handleSubmit}>add</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddOffer
