import React, { useState, useEffect } from 'react'
import List from './List'
import Table from './Table'
import { connect } from 'react-redux'

//import a from './exampledata.json'

//const data = a.offers

export const Search = props => {
  const [data, setData] = useState(undefined)

  function getData(url) {
    fetch(`/api${url}`)
      .then(response => {
        if (response.ok) return response.json()
        else return undefined
      })
      .then(myJson => {
        setData(myJson)
      })
  }

  useEffect(() => {
    getData('/offers')
  }, [])

  const handleSubmit = async event => {
    event.preventDefault()
    var data = new FormData(event.target)
    var data2 = new FormData(event.target)
    for (var key of data2) {
      if (key[1] === '') data.delete(key[0])
    }
    const params = new URLSearchParams(data).toString()

    console.log(params)

    getData('/offers?' + params)
  }

  const clickOffer = id => {
    props.history.push(`/offer/${id}`)
  }

  return (
    <div>
      <div className="searchmain flexing search">
        <div className="content-box col25 center">
          {props.lang.filters}
          <form onSubmit={handleSubmit}>
            <table>
              <tbody>
                <tr>
                  <td>{props.lang.name}</td>
                  <td> : </td>
                  <td>
                    <input
                      className="textfilter"
                      id="name"
                      name="SearchName"
                      type="text"
                    />
                  </td>
                </tr>
                <tr>
                  <td>{props.lang.price}</td>
                  <td> : </td>
                  <td className="center">
                    <input
                      className="numberfilter"
                      name="MinPrice"
                      type="number"
                    />
                    -
                    <input
                      className="numberfilter"
                      name="MaxPrice"
                      type="number"
                    />
                  </td>
                </tr>
                <tr>
                  <td>{props.lang.localization}</td>
                  <td> : </td>
                  <td>
                    <input
                      className="textfilter"
                      name="Localization"
                      type="text"
                    />
                  </td>
                </tr>
                <tr>
                  <td>{props.lang.category}</td>
                  <td> : </td>
                  <td>
                    <input className="textfilter" name="Category" type="text" />
                  </td>
                </tr>
                <tr>
                  <td>{props.lang.status}</td>
                  <td> : </td>
                  <td>
                    <input
                      className="textfilter"
                      name="Status"
                      type="checkbox"
                    />
                  </td>
                </tr>
                <tr>
                  <td>{props.lang.rating}</td>
                  <td> : </td>
                  <td className="center">
                    <input
                      className="numberfilter"
                      name="MinRating"
                      type="number"
                    />
                    -
                    <input
                      className="numberfilter"
                      name="MaxRating"
                      type="number"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
            <input
              className="description btn-blue"
              type="submit"
              value={props.lang.send}
            />
          </form>
        </div>
        <div className="content-box col75 centering">
          {data ? (
            <Table
              data={data.map(offer => (
                <List
                  key={offer.id}
                  id={offer.id}
                  picture={offer.picture}
                  title={offer.name}
                  cook={offer.firstname + ' ' + offer.lastname}
                  date={offer.day}
                  localization={offer.localization}
                  grade={offer.grade}
                  price={offer.price}
                  description={offer.description}
                  onClick={() => clickOffer(offer.id)}
                ></List>
              ))}
            />
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
