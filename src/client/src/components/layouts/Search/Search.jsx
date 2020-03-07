import React, { useState, useEffect } from 'react'
import List from './List'
import { connect } from 'react-redux'
import a from './exampledata.json'

const data = a.offers

export const Search = props => {
  // const [data, setData] = useState(undefined)

  // function getData(url){
  //   fetch(url)
  //   .then((response) => {
  //     return response.json();
  //   })
  //   .then((myJson) => {
  //     setData(myJson.offers);
  //   });
  // }

  // useEffect(() => {
  //getData('http://192.168.99.100:5001/js');
  // }, []);

  const handleSubmit = async event => {
    event.preventDefault()
    var data = new FormData(event.target)
    var data2 = new FormData(event.target)
    for (var key of data2) {
      if (key[1] == '') data.delete(key[0])
    }
    const params = new URLSearchParams(data).toString()
    console.log(params)
    fetch('url' + '?' + params, {
      method: 'GET',
    })
      .then(response => {
        return response.json()
      })
      .then(myJson => {
        //         setData(myJson.offers);
      })
  }

  return (
    <div>
      <div className="content-box searchmain flexing search">
        <label htmlFor="what">{props.lang.what} </label>
        <input id="what" type="text" />
        <label htmlFor="localization">{props.lang.localization} </label>
        <input id="localization" type="text" />
        <button className="btn-blue">{props.lang.search} </button>
      </div>
      <div className="searchmain flexing">
        <div className="content-box col25 center">
          {props.lang.filters}
          <form onSubmit={handleSubmit}>
            <table>
              <tr>
                <td>nazwa</td>
                <td> : </td>
                <td>
                  <input
                    className="textfilter"
                    id="name"
                    name="name"
                    type="text"
                  />
                </td>
              </tr>
              <tr>
                <td>cena</td>
                <td> : </td>
                <td className="center">
                  <input
                    className="numberfilter"
                    name="min-price"
                    type="number"
                  />
                  -
                  <input
                    className="numberfilter"
                    name="max-price"
                    type="number"
                  />
                </td>
              </tr>
              <tr>
                <td>lokalizacja</td>
                <td> : </td>
                <td>
                  <input
                    className="textfilter"
                    name="localization"
                    type="text"
                  />
                </td>
              </tr>
              <tr>
                <td>kategoria</td>
                <td> : </td>
                <td>
                  <input className="textfilter" name="category" type="text" />
                </td>
              </tr>
              <tr>
                <td>status</td>
                <td> : </td>
                <td>
                  <input className="textfilter" name="status" type="checkbox" />
                </td>
              </tr>
              <tr>
                <td>ocena</td>
                <td> : </td>
                <td className="center">
                  <input
                    className="numberfilter"
                    name="min-grade"
                    type="number"
                  />
                  -
                  <input
                    className="numberfilter"
                    name="max-grade"
                    type="number"
                  />
                </td>
              </tr>
            </table>
            <input className="description" type="submit" />
          </form>
        </div>
        <div className="content-box col75 centering">
          {data ? (
            data.map(offer => (
              <List
                key={offer.id}
                id={offer.id}
                picture={offer.picture}
                title={offer.title}
                cook={offer.cook}
                date={offer.date}
                location={offer.location}
                grade={offer.grade}
                price={offer.price}
                description={offer.description}
              ></List>
            ))
          ) : (
            <div id="dots1">
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.search,
})

export default connect(mapStateToProps)(Search)
