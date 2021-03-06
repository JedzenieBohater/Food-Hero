import React, { useState, useEffect } from 'react'
import { connect } from 'react-redux'
const DEFAULT_SELECT = '5'

export const Offer = props => {
  const [data, setData] = useState(undefined)
  const [isData, setIsData] = useState(true)
  const [gradeSent, setGradeSent] = useState(false)
  const [grade, setGrade] = useState(DEFAULT_SELECT)
  const [dishImgUrl, setDishImgUrl] = useState(null)

  const getOffer = async url => {
    try {
      let response = await fetch(url)

      if (!response.ok) {
        throw new Error('getOffer response not ok')
      }

      let data = await response.json()
      setData(data)

      response = await fetch(`/api/dish/${data.id_dish}/image`)

      if (!response.ok) {
        throw new Error('Getting dish img not ok')
      }

      data = await response.blob()
      const urlData = URL.createObjectURL(data)
      setDishImgUrl(urlData)
    } catch (err) {
      setIsData(false)
      console.log(err)
    }
  }

  useEffect(() => {
    getOffer(`/api/offers/${props.match.params.id}`)
    // TODO get info about offer_rate
    // set gradeSent true if user already rated offer
  }, [])

  function goPayment() {
    console.log('tutaj bedzie sie płacić')
  }

  const rateOffer = async () => {
    try {
      const response = await fetch('/api/offer_rating', {
        method: 'POST',
        body: JSON.stringify({ grade, offerId: props.match.params.id }),
      })

      if (!response.ok) {
        throw new Error('rateOffer not ok')
      }

      setGradeSent(true)
    } catch (err) {
      console.log(err)
    }
  }

  return (
    <div className="content-box flexcolumn search searchmain">
      {!!isData ? (
        data ? (
          <div>
            <div
              className="content-box marginer title centering"
              onClick={() => console.log(props.id)}
            >
              {props.lang.offerDetails}:
            </div>
            <div className="flexrow">
              <div className="">
                <img
                  className="descpic"
                  alt=""
                  src={
                    !!dishImgUrl
                      ? dishImgUrl
                      : `${process.env.PUBLIC_URL}/static/images/logo.svg`
                  }
                />
                {!gradeSent ? (
                  <div>
                    <div className="centering"> {props.lang.rateit}!</div>
                    <div className="centering">
                      <select
                        id="grade"
                        defaultValue={DEFAULT_SELECT}
                        onChange={e => setGrade(e.target.value)}
                      >
                        <option value="5">5</option>
                        <option value="4">4</option>
                        <option value="3">3</option>
                        <option value="2">2</option>
                        <option value="1">1</option>
                      </select>
                      <span>★</span>
                    </div>
                    <button onClick={rateOffer} className="ratebutton">
                      {props.lang.send}
                    </button>
                  </div>
                ) : (
                  <div className="centering">{props.lang.sent}!</div>
                )}
              </div>
              <div className="col75">
                <div className="title centering">
                  <b>{data.name}</b>
                </div>
                <div className="flexrow flexjustify">
                  <div className="marginer price">{data.price} zł</div>
                  <div className="marginer">
                    {data.firstname + ' ' + data.lastname}
                  </div>
                  <div className="marginer">{data.day}</div>
                  <div className="marginer">{data.localization}</div>
                </div>
                <span className="description marginer">{data.description}</span>
              </div>
            </div>
            <button
              className="ratebutton paybutton"
              onClick={() => goPayment()}
            >
              {props.lang.pay}
            </button>
          </div>
        ) : (
          <div />
        )
      ) : (
        <div
          id={props.id}
          className="content-box marginer title centering"
          onClick={() => console.log(props.id)}
        >
          {props.lang.unavailable}
        </div>
      )}
    </div>
  )
}

const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.offer,
})

export default connect(mapStateToProps)(Offer)
